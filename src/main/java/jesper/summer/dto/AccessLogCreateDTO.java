package jesper.summer.dto;

import javax.validation.constraints.NotNull;

// AccessLogCreateDTO.java
public class AccessLogCreateDTO {
    @NotNull
    private Long deviceId;

    @NotNull
    private Long personId;

    @NotNull
    private String accessType;

    // getters and setters

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}