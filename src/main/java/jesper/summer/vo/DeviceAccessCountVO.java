package jesper.summer.vo;

import lombok.Data;

@Data
public class DeviceAccessCountVO {
    private String deviceId;
    private Long count;
    public DeviceAccessCountVO(String deviceId, Long count) {
        this.deviceId = deviceId;
        this.count = count;
    }
}