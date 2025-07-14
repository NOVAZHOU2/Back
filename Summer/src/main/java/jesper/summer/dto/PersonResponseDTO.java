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

    // 静态Builder类
    public static class Builder {
        private String name;
        private Integer gender;
        private String idCard;
        private String phone;
        private String position;
        private Integer status;
        private FaceDataDTO faceData;

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

        public PersonResponseDTO build() {
            PersonResponseDTO dto = new PersonResponseDTO();
            dto.setName(this.name);
            dto.setGender(this.gender);
            dto.setIdCard(this.idCard);
            dto.setPhone(this.phone);
            dto.setPosition(this.position);
            dto.setStatus(this.status);
            dto.setFaceData(this.faceData);
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
        private String featureData;
        private String imagePath;
        private Float qualityScore;
        private String version;

        public FaceDataDTO(String featureData, String imagePath, Float qualityScore, String version) {
            this.featureData = featureData;
            this.imagePath = imagePath;
            this.qualityScore = qualityScore;
            this.version = version;
        }
    }
}
