package jesper.summer.service;

import jesper.summer.dto.AccessDeviceDTO;
import jesper.summer.dto.AccessLogDTO;
import jesper.summer.entity.AccessDevice;
import jesper.summer.exception.BusinessException;

import java.util.List;

public interface AccessDeviceService {
    AccessDevice create(AccessDevice device);
    AccessDevice getById(String deviceId);
    List<AccessDevice> getAll();
    AccessDevice update(AccessDevice device);
    void delete(String deviceId);
}