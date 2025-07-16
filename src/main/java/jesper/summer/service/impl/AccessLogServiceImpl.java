package jesper.summer.service.impl;

import jesper.summer.entity.AccessLog;
import jesper.summer.repository.AccessLogRepository;
import jesper.summer.service.AccessLogService;
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

    @Override
    public String delete(Long logId) {
        if (!repository.existsById(logId)) {
            return "日志不存在";
        }
        repository.deleteById(logId);
        return "删除成功";
    }
}