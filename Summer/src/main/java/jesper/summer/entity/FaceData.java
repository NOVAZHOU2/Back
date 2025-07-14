package jesper.summer.entity;

import jesper.summer.entity.Person;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "face_data")
public class FaceData {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL 自增
    private Long personId;

    @Lob
    @Column(name = "feature_data", nullable = false)
    private byte[] featureData;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Column(name = "register_time", updatable = false)
    private LocalDateTime registerTime;

    @Column(name = "quality_score")
    private Float qualityScore;

    @Column(length = 20)
    private String version;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

    // Getters and Setters

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public byte[] getFeatureData() {
        return featureData;
    }

    public void setFeatureData(byte[] featureData) {
        this.featureData = featureData;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public Float getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Float qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}