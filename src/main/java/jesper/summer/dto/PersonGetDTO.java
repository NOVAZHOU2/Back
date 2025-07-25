package jesper.summer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonGetDTO {
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度需在2-20个字符之间")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
