package jesper.summer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

// BatchPersonDeleteDTO.java
@Data
public class BatchPersonDeleteDTO {
    @NotEmpty(message = "姓名列表不能为空")
    private List<String> names;
}

