package jesper.summer.repository;

import jesper.summer.entity.AccessLog;
import jesper.summer.vo.DeviceAccessCountVO;
import jesper.summer.vo.VisitorRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// AccessLogRepository.java
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    // 主键类型为Long（假设access_log表有自增主键）
    @Query("SELECT log.logId FROM AccessLog log WHERE log.deviceId = :deviceId")
    List<Long> findByDeviceId(String deviceId);

    @Query("SELECT new jesper.summer.vo.DeviceAccessCountVO(log.deviceId, COUNT(log)) " +
            "FROM AccessLog log " +
            "WHERE DATE(log.accessTime) = CURRENT_DATE AND log.result = 1 " +
            "GROUP BY log.deviceId")
    List<DeviceAccessCountVO> countTodayAccessByDevice1();

    @Query("SELECT new jesper.summer.vo.DeviceAccessCountVO(log.deviceId, COUNT(log)) " +
            "FROM AccessLog log " +
            "WHERE DATE(log.accessTime) = CURRENT_DATE AND log.result = 0 " +
            "GROUP BY log.deviceId")
    List<DeviceAccessCountVO> countTodayAccessByDevice0();

    @Query("SELECT " +
            "  new jesper.summer.vo.VisitorRatio(pd.position,COUNT(al)) " +
            "FROM AccessLog al " +
            "JOIN al.person p " +
            "JOIN p.detail pd " +
            "WHERE DATE(al.accessTime) = CURRENT_DATE " +
            "  AND al.result = 1 " +
            "GROUP BY pd.position")  // 直接按position分组
    List<VisitorRatio> countPerson();

    // 查询温度大于指定值的日志
    List<AccessLog> findByTemperatureGreaterThan(Float temperature);

    // 查询指定时间段内的日志（23:00-6:00）
    @Query("SELECT a FROM AccessLog a WHERE HOUR(a.accessTime) >= 23 OR HOUR(a.accessTime) < 6")
    List<AccessLog> findNightTimeLogs();

    @Modifying  // 必须添加
    @Query("DELETE FROM AccessLog a WHERE a.person.name = :name")
    int deleteByPersonName(@Param("name") String name);
}
