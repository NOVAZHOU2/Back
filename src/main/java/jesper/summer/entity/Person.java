package jesper.summer.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;



@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    // 正确关联PersonDetail（假设通过person_id外键）
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private PersonDetail detail; // 字段名必须与查询语句一致

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private FaceData faceData; // 字段名必须与查询语句一致
    // Getters and Setters

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AccessLog> accessLog;

    public FaceData getFacedata() {
        return faceData;
    }

    public void setFacedata(FaceData facedata) {
        this.faceData = facedata;
    }

    public PersonDetail getDetail() {
        return detail;
    }

    public void setDetail(PersonDetail detail) {
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccessLog> getAccessLog() {
        return accessLog;
    }
}