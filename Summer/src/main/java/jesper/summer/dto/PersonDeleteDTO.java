package jesper.summer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// jesper.summer.dto.PersonDeleteDTO
@Data
public class PersonDeleteDTO {
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度需在2-20个字符之间")
    private String name;
}