package jesper.summer.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

// jesper.summer.dto.PersonUpdateByNameDTO
@Data
public class PersonUpdateByNameDTO {
    @NotBlank(message = "姓名不能为空")
    private String name;
    private Integer gender;
    private String idCard;
    private String phone;
    private String position;
    private Integer status;
    private PersonCreateDTO.FaceDataDTO faceData;

    @Data
    public static class FaceDataDTO {
        private String featureData; // Base64编码
        private String imagePath;
        private Float qualityScore;
        private String version;
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

    public PersonCreateDTO.FaceDataDTO getFaceData() {
        return faceData;
    }

    public void setFaceData(PersonCreateDTO.FaceDataDTO faceData) {
        this.faceData = faceData;
    }
}