package jesper.summer.repository;

import jesper.summer.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// AccessLogRepository.java
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    // 主键类型为Long（假设access_log表有自增主键）
    @Query("SELECT log.logId FROM AccessLog log WHERE log.deviceId = :deviceId")
    List<Long> findByDeviceId(String deviceId);
}
