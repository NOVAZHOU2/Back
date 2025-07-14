package jesper.summer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// jesper.summer.dto.OperationResultDTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResultDTO {
    private String operation;
    private String targetName;
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}