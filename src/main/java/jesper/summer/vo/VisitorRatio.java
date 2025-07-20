package jesper.summer.vo;

import lombok.Data;

@Data
public class VisitorRatio {
    private String position;
    private Long count;
    public VisitorRatio(String position, Long count) {
        this.position = position;
        this.count = count;
    }

}
