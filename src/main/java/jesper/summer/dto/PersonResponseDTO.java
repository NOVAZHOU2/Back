package jesper.summer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonResponseDTO {
    private Long personId;
    private String name;
    private Integer gender;
    private String idCard;
    private String phone;
    private String position;
    private Integer status;
    private LocalDateTime registerTime;
    private FaceDataDTO faceData;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

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

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public FaceDataDTO getFaceData() {
        return faceData;
    }

    public void setFaceData(FaceDataDTO faceData) {
        this.faceData = faceData;
    }

    // 静态Builder类
    public static class Builder {
        private Long personId;
        private String name;
        private Integer gender;
        private String idCard;
        private String phone;
        private String position;
        private Integer status;
        private FaceDataDTO faceData;
        private LocalDateTime registerTime;

        public Builder personId(Long personId) {
            this.personId = personId;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(Integer gender) {
            this.gender = gender;
            return this;
        }
        public Builder idCard(String idCard) {
            this.idCard = idCard;
            return this;
        }
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder position(String position) {
            this.position = position;
            return this;
        }
        public Builder status(Integer status) {
            this.status = status;
            return this;
        }
        // 其他字段的链式方法...
        public Builder faceData(FaceDataDTO faceData) {
            this.faceData = faceData;
            return this;
        }
        public Builder registerTime(LocalDateTime registerTime) {
            this.registerTime = registerTime;
            return this;
        }
        public PersonResponseDTO build() {
            PersonResponseDTO dto = new PersonResponseDTO();
            dto.setPersonId(this.personId);
            dto.setName(this.name);
            dto.setGender(this.gender);
            dto.setIdCard(this.idCard);
            dto.setPhone(this.phone);
            dto.setPosition(this.position);
            dto.setStatus(this.status);
            dto.setFaceData(this.faceData);
            dto.setRegisterTime(this.registerTime);
            return dto;
        }


    }

    // 提供builder()入口
    public static Builder builder() {
        return new Builder();
    }

    // 嵌套的FaceDataDTO（也需支持Builder）
    @Data
    public static class FaceDataDTO {
        private String faceToken;
        private String groupId;
        private Long logId;
        private String faceUrl;

        public FaceDataDTO(String faceToken, String groupId, Long logId,String faceUrl) {
            this.faceToken = faceToken;
            this.groupId = groupId;
            this.logId = logId;
            this.faceUrl = faceUrl;
        }
    }
}
