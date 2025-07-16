package jesper.summer.repository;

import jesper.summer.entity.PersonDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonDetailRepository extends JpaRepository<PersonDetail, Long> {
    boolean existsByIdCard(String idCard);

    @Modifying
    @Query("DELETE FROM PersonDetail pd WHERE pd.name = :name")
    void deleteDetailByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE PersonDetail pd SET " +
            "pd.gender = COALESCE(:gender, pd.gender), " +
            "pd.idCard = COALESCE(:idCard, pd.idCard), " +
            "pd.phone = COALESCE(:phone, pd.phone), " +
            "pd.position = COALESCE(:position, pd.position), " +
            "pd.status = COALESCE(:status, pd.status) " +
            "WHERE pd.personId = :personId")
    int updateSelectiveByPersonId(
            @Param("personId") Long personId,
            @Param("gender") Integer gender,
            @Param("idCard") String idCard,
            @Param("phone") String phone,
            @Param("position") String position,
            @Param("status") Integer status
    );
    @EntityGraph(attributePaths = {"person"})
    Optional<PersonDetail> findByPersonId(Long personId);
}