package jesper.summer.repository;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jesper.summer.entity.Person;
import jesper.summer.entity.PersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // 添加根据姓名删除的方法
    @Modifying
    @Query("DELETE FROM Person p WHERE p.name = :name")
    void deleteByName(@Param("name") String name);

    // 同时需要添加级联删除的方法（如果需要）
    @Modifying
    @Query("DELETE FROM PersonDetail pd WHERE pd.name = :name")
    void deleteDetailByName(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM FaceData fd WHERE fd.person.name = :name")
    void deleteFaceDataByName(@Param("name") String name);

    // 通过姓名查找用户（包含关联的PersonDetail）
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.detail WHERE p.name = :name")
    Person findByNameWithDetail(@Param("name") String name);

    // 通过姓名查找用户ID（用于更新操作）
    @Query("SELECT p.id FROM Person p WHERE p.name = :name")
    Optional<Long> findIdByName(@Param("name") String name);

}