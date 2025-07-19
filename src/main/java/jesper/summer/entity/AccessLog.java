package jesper.summer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jesper.summer.entity.AccessDevice;
import jesper.summer.entity.Person;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "access_log")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 关键配置：自动增长
    @Column(name = "log_id")  // 新增主键（图片中未显示但用户要求添加）
    private Long logId;

    @Column(name = "person_id")
    private Long personId;  // bigint

    @CreationTimestamp
    @Column(name = "access_time", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime accessTime;  // datetime

    @Column(name = "access_type")
    private Integer accessType;  // tinyint (1-人脸,2-刷卡,3-混合)

    @Column(name = "result")
    private Integer result;  // tinyint (0-拒绝,1-通过)

    @Column(name = "temperature")
    private Float temperature;  // float

    @Column(name = "confidence")
    private Float confidence;  // float

    @Column(name = "capture_image", length = 255)
    private String captureImage;  // varchar(255)

    @Column(name = "device_id", length = 36)
    private String deviceId;  // varchar(36)

    // 关联实体（根据外键约束可选添加）
    @JsonIgnore  // 序列化时忽略此字段
    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @JsonIgnore  // 序列化时忽略此字段
    @ManyToOne
    @JoinColumn(name = "device_id", insertable = false, updatable = false)
    private AccessDevice device;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public String getCaptureImage() {
        return captureImage;
    }

    public void setCaptureImage(String captureImage) {
        this.captureImage = captureImage;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AccessDevice getDevice() {
        return device;
    }

    public void setDevice(AccessDevice device) {
        this.device = device;
    }
}