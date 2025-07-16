package jesper.summer.entity;

import jesper.summer.entity.Person;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person_detail")
public class PersonDetail {
    @Id
    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false, length = 50)
    private String name;

    private Integer gender;

    @Column(unique = true, length = 18)
    private String idCard;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String position;

    @Column(name = "register_time", updatable = false)
    private LocalDateTime registerTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}