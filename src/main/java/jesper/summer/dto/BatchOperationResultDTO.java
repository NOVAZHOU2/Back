package jesper.summer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// BatchOperationResultDTO.java
@Data
@AllArgsConstructor
public class BatchOperationResultDTO {
    private String operation;
    private List<String> targetNames;
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private int successCount;
    private int failureCount;
}
