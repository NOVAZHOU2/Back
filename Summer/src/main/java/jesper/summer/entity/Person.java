package jesper.summer.entity;


import java.time.LocalDateTime;
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
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private PersonDetail detail; // 字段名必须与查询语句一致
    // Getters and Setters

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
}