package jesper.summer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jesper.summer.entity.Person;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "face_data")
public class FaceData {

    @Id
    @Column(name = "person_id")
    private Long personId;

    @Lob
    @Column(name = "face_token", nullable = false)
    private String faceToken;

    @Column(name = "group_id", length = 255)
    private String groupId;

    @Column(name = "register_time", updatable = false)
    private LocalDateTime registerTime;

    @Column(name = "log_id")
    private Long logId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    // Getters and Setters


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}