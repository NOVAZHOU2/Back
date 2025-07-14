package jesper.summer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

// jesper.summer.dto.FaceDataDTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceDataDTO {
    @NotBlank(message = "特征数据不能为空")
    private String featureData; // Base64编码的二进制特征数据

    @NotBlank(message = "图片路径不能为空")
    private String imagePath;

    @DecimalMin("0.0") @DecimalMax("100.0")
    private Float qualityScore;

    @NotBlank(message = "算法版本不能为空")
    private String version;

    private LocalDateTime registerTime; // 注册时间（可选）

    public FaceDataDTO(String s, String imagePath, Float qualityScore, String version) {
    }
}