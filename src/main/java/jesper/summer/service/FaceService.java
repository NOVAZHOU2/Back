package jesper.summer.service;

import jesper.summer.entity.Person;
import jesper.summer.entity.PersonDetail;
import jesper.summer.exception.BaiduApiException;
import jesper.summer.exception.BusinessException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FaceService {
    JSONObject registerFace(Long personId, MultipartFile file, String groupId, String faceUrl,String userInfo) throws BaiduApiException, IOException, BusinessException;


    JSONObject updateFace(Long personId, MultipartFile file, String groupId, String faceUrl,String userInfo) throws BaiduApiException, IOException;

    PersonDetail recognizeFace(MultipartFile file, String groupIdList) throws BaiduApiException, IOException;

    JSONObject deleteFace(Long personId) throws BaiduApiException;
}
