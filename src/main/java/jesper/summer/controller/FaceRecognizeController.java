package jesper.summer.controller;

import jesper.summer.entity.Person;
import jesper.summer.entity.PersonDetail;
import jesper.summer.exception.BaiduApiException;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.FaceService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/recognize")
@Slf4j
public class FaceRecognizeController {
    private final FaceService faceService;

    @Autowired
    public FaceRecognizeController(FaceService faceService) {
        this.faceService = faceService;
    }

    // 人脸注册接口
    @PostMapping("/register")
    public ResponseEntity<?> registerFace(
            @RequestParam Long personId,
            @RequestPart("file") MultipartFile file,
            @RequestParam String groupId,
            @RequestParam String faceUrl,
            @RequestParam(required = false) String userInfo) throws BaiduApiException, IOException, BusinessException {

        JSONObject result = faceService.registerFace(
                personId,
                file,
                groupId,
                faceUrl,
                userInfo
        );
        return ResponseEntity.ok(result.toMap());
    }

    // 人脸识别接口
    @PostMapping("/recognize")
    public ResponseEntity<?> recognizeFace(
            @RequestPart("file") MultipartFile file,
            @RequestParam String faceUrl,
            @RequestParam String groupIdList) throws BaiduApiException, IOException {

        PersonDetail result = faceService.recognizeFace(
                file,
                groupIdList
        );
        log.info(result.toString());
        return ResponseEntity.ok(result);
    }

    // 人脸删除接口
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFace(@RequestParam Long personId) throws BaiduApiException {

        JSONObject result = faceService.deleteFace(
                personId
        );
        return ResponseEntity.ok(result.toMap());
    }


    // 人脸更新接口
    @PutMapping("/update")
    public ResponseEntity<?> updateFace(
            @RequestParam Long personId,
            @RequestPart("file") MultipartFile file,
            @RequestParam String groupId,
            @RequestParam String faceUrl,
            @RequestParam(required = false) String userInfo) throws BaiduApiException, IOException {

        JSONObject result = faceService.updateFace(
                personId,
                file,
                groupId,
                faceUrl,
                userInfo
        );
        return ResponseEntity.ok(result.toMap());
    }


}
