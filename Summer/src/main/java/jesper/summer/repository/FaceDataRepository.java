package jesper.summer.repository;

import jesper.summer.entity.FaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FaceDataRepository extends JpaRepository<FaceData, Long> {
    @Modifying
    @Query("DELETE FROM FaceData fd WHERE fd.person.name = :name")
    void deleteFaceDataByName(@Param("name") String name);

    // 更新或插入人脸数据
    @Modifying
    @Query("UPDATE FaceData fd SET " +
            "fd.featureData = :featureData, " +
            "fd.imagePath = :imagePath, " +
            "fd.qualityScore = :qualityScore, " +
            "fd.version = :version " +
            "WHERE fd.personId = :personId")
    int upsertFaceData(
            @Param("personId") Long personId,
            @Param("featureData") byte[] featureData,
            @Param("imagePath") String imagePath,
            @Param("qualityScore") Float qualityScore,
            @Param("version") String version
    );

    Optional<FaceData> findByPersonId(Long personId);
}