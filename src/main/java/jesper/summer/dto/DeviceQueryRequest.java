package jesper.summer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data // Lombok注解
public class DeviceQueryRequest {
    @NotBlank(message = "设备ID不能为空")
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
