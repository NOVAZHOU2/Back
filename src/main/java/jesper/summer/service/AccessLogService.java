package jesper.summer.service;

import jesper.summer.entity.AccessLog;
import jesper.summer.vo.DeviceAccessCountVO;
import jesper.summer.vo.VisitorRatio;

import java.util.List;

public interface AccessLogService {
    AccessLog create(AccessLog log);
    List<AccessLog> getAll();
    String delete(Long logId);

    int batchDelete(List<Long> logIds);

    List<DeviceAccessCountVO> getDeviceAccessCount();
    List<DeviceAccessCountVO> getDeviceAccessCountStop();

    List<VisitorRatio> getPersonCount();

    List<AccessLog> getHighTemperatureLogs();

    List<AccessLog> getNightTimeLogs();
}