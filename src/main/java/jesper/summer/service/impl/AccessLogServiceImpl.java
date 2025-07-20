package jesper.summer.service.impl;

import jesper.summer.entity.AccessLog;
import jesper.summer.repository.AccessLogRepository;
import jesper.summer.service.AccessLogService;
import jesper.summer.vo.DeviceAccessCountVO;
import jesper.summer.vo.VisitorRatio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// AccessLogServiceImpl.java
@Service
@RequiredArgsConstructor
public class AccessLogServiceImpl implements AccessLogService {
    private final AccessLogRepository repository;

    @Override
    public AccessLog create(AccessLog log) {
        return repository.save(log);
    }

    @Override
    public List<AccessLog> getAll() {
        return repository.findAll();
    }

    public int batchDelete(List<Long> logIds) {
        // 批量删除（物理删除）
        for(Long logId : logIds){
            repository.deleteById(logId);
        }
        return logIds.size();

        /* 若需逻辑删除，可改为：
        List<AccessLog> logs = logRepository.findAllById(logIds);
        logs.forEach(log -> log.setDeleted(true));
        return logRepository.saveAll(logs).size(); */
    }
    @Override
    public String delete(Long logId) {
        if (!repository.existsById(logId)) {
            return "日志不存在";
        }
        repository.deleteById(logId);
        return "删除成功";
    }

    @Override
    public List<DeviceAccessCountVO> getDeviceAccessCount() {
        return repository.countTodayAccessByDevice1();
    }

    public List<DeviceAccessCountVO> getDeviceAccessCountStop(){
        return repository.countTodayAccessByDevice0();
    }

    @Override
    public List<VisitorRatio> getPersonCount() {
        return repository.countPerson();
    }

    public List<AccessLog> getHighTemperatureLogs() {
        return repository.findByTemperatureGreaterThan(37.5f);
    }

    public List<AccessLog> getNightTimeLogs() {
        // 方法1：使用HOUR函数查询
        return repository.findNightTimeLogs();
    }
}