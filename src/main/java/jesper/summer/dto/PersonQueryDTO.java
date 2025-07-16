package jesper.summer.dto;

import lombok.Data;

// jesper.summer.dto.PersonQueryDTO
@Data
public class PersonQueryDTO {
    private String name;       // 姓名模糊查询
    private Integer gender;    // 性别精确匹配
    private Integer status;    // 状态精确匹配
    private String phoneLike;  // 手机号模糊查询

    // 分页参数（与Pageable兼容）
    private Integer page = 0;
    private Integer size = 10;
    private String sort = "id,asc";
}