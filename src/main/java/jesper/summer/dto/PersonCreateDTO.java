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
        private String faceToken; // Base64编码
        private Long logId;
        private String groupId;
        private String faceUrl;

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getFaceToken() {
            return faceToken;
        }

        public void setFaceToken(String faceToken) {
            this.faceToken = faceToken;
        }

        public Long getLogId() {
            return logId;
        }

        public void setLogId(Long logId) {
            this.logId = logId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }
    }
}