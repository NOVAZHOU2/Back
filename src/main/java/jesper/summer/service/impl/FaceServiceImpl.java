package jesper.summer.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jesper.summer.config.BaiduFaceResultHandler;
import jesper.summer.entity.FaceData;
import jesper.summer.entity.Person;
import jesper.summer.exception.BaiduApiException;
import jesper.summer.exception.BusinessException;
import jesper.summer.repository.FaceDataRepository;
import jesper.summer.repository.PersonRepository;
import jesper.summer.service.FaceService;
import jesper.summer.utils.FaceUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@Service
public class FaceServiceImpl implements FaceService {
    private final FaceUtils faceUtils;
    private final FaceDataRepository faceDataMapper;
    private final PersonRepository personMapper;

    @Autowired
    public FaceServiceImpl(FaceUtils faceUtils, FaceDataRepository faceDataMapper, PersonRepository personMapper) {
        this.faceUtils = faceUtils;
        this.faceDataMapper = faceDataMapper;
        this.personMapper = personMapper;
    }

    // 人脸注册（含数据库记录）
    @Transactional
    public JSONObject registerFace(Long personId, MultipartFile file, String groupId,String faceUrl, String userInfo) throws BaiduApiException, IOException, BusinessException {
        // 调用百度API注册
        // 1. 先检查是否已存在记录（避免重复注册）
        if(faceDataMapper.existsById(personId)) {
            throw new BusinessException("该人员已注册过人脸");
        }
        String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        JSONObject result = BaiduFaceResultHandler.handleResult(faceUtils.registerFace(imageBase64, groupId, personId.toString(), userInfo));

        JSONObject faceResult = result.getJSONObject("result");
        String faceToken = faceResult.getString("face_token");
        Long logId = result.getLong("log_id");
        Person person = personMapper.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        // 构建数据库实体

        FaceData faceData = new FaceData();
        faceData.setPersonId(personId);
        faceData.setFaceToken(faceToken);
        faceData.setGroupId(groupId);
        faceData.setLogId(logId);
        faceData.setFaceUrl(faceUrl);
        faceData.setRegisterTime(LocalDateTime.now());


        // 存储到数据库
        log.info("Face register: " + faceData.getPersonId());
        faceDataMapper.save(faceData);

        return result;
    }

    // 人脸识别（1:N搜索）
    public Person recognizeFace(MultipartFile file, String groupIdList) throws BaiduApiException, IOException {
        String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        JSONObject result = BaiduFaceResultHandler.handleResult(faceUtils.recognizeFace(imageBase64, groupIdList));
        JSONObject resultObj = result.getJSONObject("result");
        Long userId = 0L;
        JSONArray userList = resultObj.getJSONArray("user_list");

        if (userList.length() > 0) {
            // 获取第一个匹配的用户（通常是分数最高的）
            JSONObject firstUser = userList.getJSONObject(0);

            // 提取 user_id
            userId = firstUser.getLong("user_id");

            // 使用 userId
            System.out.println("识别到的用户 ID: " + userId);

        } else {
            System.out.println("未识别到匹配的用户");
        }

        log.info("Face recognize: " + userId);
        return personMapper.getPersonById(userId);
    }

    // 人脸删除（含数据库清理）
    @Transactional
    public JSONObject deleteFace(Long personId) throws BaiduApiException {
        // 调用百度API删除

        FaceData faceData = faceDataMapper.getReferenceById(personId);
        JSONObject result =  BaiduFaceResultHandler.handleResult(faceUtils.deleteFace(
                personId.toString(),
                faceData.getGroupId(),
                faceData.getFaceToken()
        ));

        // 成功时删除数据库记录
        faceDataMapper.deleteByFaceToken(faceData.getFaceToken());
        return result;
    }


    // 人脸信息更新
    public JSONObject updateFace(Long personId, MultipartFile file, String groupId, String faceUrl ,String userInfo) throws BaiduApiException, IOException {

        FaceData faceData = faceDataMapper.getReferenceById(personId);
        String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        JSONObject result;
        if(!faceData.getGroupId().equals(groupId)){
            BaiduFaceResultHandler.handleResult(faceUtils.deleteFace(
                    personId.toString(),
                    faceData.getGroupId(),
                    faceData.getFaceToken()
            ));
            result = BaiduFaceResultHandler.handleResult(faceUtils.registerFace(
                    imageBase64,
                    groupId,
                    personId.toString(),
                    userInfo
            ));
        }else{
            result = BaiduFaceResultHandler.handleResult(faceUtils.updateFace(
                    imageBase64,
                    groupId,
                    personId.toString()
            ));
        }
        JSONObject faceResult = result.getJSONObject("result");
        faceDataMapper.upsertFaceData(personId,faceResult.getString("face_token"),groupId,result.getLong("log_id"),faceUrl);
        return result;
    }
}
