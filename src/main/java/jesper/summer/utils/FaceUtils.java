package jesper.summer.utils;

import com.baidu.aip.face.AipFace;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Component
@Slf4j
public class FaceUtils {

    private  String appId ="119499427";
    private  String apiKey="9BVVD2tzTetaizb4JmaUZDBC";
    private  String secretKey="Xs3B1e9tK6pe2R7jYA5opCOedQtvPQGh";
    private final ImageUtil imageUtil;
    private final AipFace aipFace;

    // 使用构造器注入参数
    public FaceUtils(
            @Value("${baidu.face.app-id}") String appId1,
            @Value("${baidu.face.api-key}") String apiKey1,
            @Value("${baidu.face.secret-key}") String secretKey1
    ) {

        AipFace client = new AipFace(this.appId, this.apiKey, this.secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        this.aipFace = client;
        ImageUtil imageUtil = new ImageUtil();
        this.imageUtil = imageUtil;
        log.info("apiId:"+this.appId);
        log.info("apiKey:" + this.apiKey);
        log.info("secretKey:" + this.secretKey);

    }

    // 创建AipFace实例的工厂方法
    private AipFace createAipFaceClient() {
        return aipFace;
    }

    // 人脸注册方法
    public JSONObject registerFace(String imageBase64, String groupId, String userId, String userInfo) {
        AipFace client = createAipFaceClient();

        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        return client.addUser(imageBase64, "BASE64", groupId, userId, options);
    }

    // 人脸识别方法（1:N搜索）
    public JSONObject recognizeFace(String imageBase64, String groupIdList) {
        AipFace client = createAipFaceClient();

        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "1");

        return client.search(imageBase64, "BASE64", groupIdList, options);
    }

    // 人脸删除方法
    public JSONObject deleteFace(String userId, String groupId, String faceToken) {
        HashMap<String, String> options = new HashMap<String, String>();
        AipFace client = createAipFaceClient();
        return client.faceDelete(userId, groupId, faceToken,options);
    }


    // 人脸更新方法
    public JSONObject updateFace(String imageBase64, String groupId, String userId) {
        AipFace client = createAipFaceClient();

        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        return client.updateUser(imageBase64, "BASE64", groupId, userId, options);
    }


}