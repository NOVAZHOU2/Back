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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public FaceDataDTO getFaceData() {
        return faceData;
    }

    public void setFaceData(FaceDataDTO faceData) {
        this.faceData = faceData;
    }

    @Data
    public static class FaceDataDTO {
        private String featureData; // Base64编码
        private String imagePath;
        private Float qualityScore;
        private String version;

        public String getFeatureData() {
            return featureData;
        }

        public void setFeatureData(String featureData) {
            this.featureData = featureData;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public Float getQualityScore() {
            return qualityScore;
        }

        public void setQualityScore(Float qualityScore) {
            this.qualityScore = qualityScore;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}