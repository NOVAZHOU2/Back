package jesper.summer.dto;

import lombok.Data;

@Data
public class PersonCreateDTO {
    private String name;
    private Integer gender;
    private String idCard;
    private String phone;
    private String position;
    private Integer status;
    private FaceDataDTO faceData;

    @Data
    public static class FaceDataDTO {
        private String featureData; // Base64编码
        private String imagePath;
        private Float qualityScore;
        private String version;
    }
}