package jesper.summer.entity;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "access_device")
@EntityListeners(AuditingEntityListener.class) // 关键监听器
public class AccessDevice {
    @Id
    @Column(name = "device_id",
            length = 36,
            nullable = false,
            columnDefinition = "VARCHAR(36) COMMENT '设备ID'")
    private String deviceId;

    @Column(name = "device_name",
            length = 100,
            nullable = false,
            columnDefinition = "VARCHAR(100) NOT NULL COMMENT '设备名称'")
    private String deviceName;

    @Column(name = "device_type",
            nullable = false,
            columnDefinition = "TINYINT NOT NULL COMMENT '设备类型:1-入口,2-出口,3-双向'")
    private Integer deviceType;

    @Column(name = "location",
            length = 200,
            nullable = false,
            columnDefinition = "VARCHAR(200) NOT NULL COMMENT '安装位置'")
    private String location;

    @Column(name = "status",
            nullable = false,
            columnDefinition = "TINYINT NOT NULL DEFAULT 1 COMMENT '状态:0-离线,1-在线,2-故障'")
    private Integer status = 1;

    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;


    @Column(name = "update_time")
    @LastModifiedDate
    LocalDateTime updateTime;

    @Column(name = "remark",
            length = 200,
            columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String remark;

    @Column(name ="longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}