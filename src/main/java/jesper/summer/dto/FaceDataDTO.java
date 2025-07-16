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
    @NotBlank(message = "faceToken不能为空")
    private String faceToken; // Base64编码的二进制特征数据

    @NotBlank(message = "groupId不能为空")
    private String groupId;

    @NotBlank(message = "groupId不能为空")
    private Long logId;

    private LocalDateTime registerTime; // 注册时间（可选）

}