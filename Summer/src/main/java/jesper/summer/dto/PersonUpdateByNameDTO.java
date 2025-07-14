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
}