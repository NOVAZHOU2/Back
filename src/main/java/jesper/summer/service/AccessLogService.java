package jesper.summer.service;

import jesper.summer.entity.AccessLog;

import java.util.List;

public interface AccessLogService {
    AccessLog create(AccessLog log);
    List<AccessLog> getAll();
    String delete(Long logId);
}