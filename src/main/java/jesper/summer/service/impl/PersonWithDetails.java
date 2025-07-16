package jesper.summer.service.impl;

import jesper.summer.entity.FaceData;
import jesper.summer.entity.PersonDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonWithDetails {
    private Long personId;
    private String name;
    private PersonDetail detail;
    private FaceData faceData;
}
