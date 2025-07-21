package jesper.summer.repository;

import jesper.summer.entity.FaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FaceDataRepository extends JpaRepository<FaceData, Long> {
    @Modifying
    @Query("DELETE FROM FaceData fd WHERE fd.person.name = :name")
    void deleteFaceDataByName(@Param("name") String name);

    // 更新或插入人脸数据
    @Transactional
    @Modifying
    @Query("UPDATE FaceData fd SET " +
            "fd.faceToken = :faceToken, " +
            "fd.groupId = :groupId, " +
            "fd.logId = :logId, " +
            "fd.faceUrl = :faceUrl " +
            "WHERE fd.personId = :personId")
    int upsertFaceData(
            @Param("personId") Long personId,
            @Param("faceToken") String faceToken,
            @Param("groupId") String groupId,
            @Param("logId") Long logId,
            @Param("faceUrl") String faceUrl
    );

    Optional<FaceData> findByPersonId(Long personId);

    FaceData findByFaceToken(String faceToken);
    void deleteByFaceToken(String faceToken);

    @Query("select fd From FaceData fd where fd.person.name =:name")
    FaceData findByPersonName(String name);
}