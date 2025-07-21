package jesper.summer.service.impl;

import jesper.summer.dto.AccessDeviceDTO;
import jesper.summer.dto.AccessLogDTO;
import jesper.summer.entity.AccessDevice;
import jesper.summer.entity.AccessLog;
import jesper.summer.exception.BusinessException;
import jesper.summer.repository.AccessDeviceRepository;
import jesper.summer.repository.AccessLogRepository;
import jesper.summer.repository.PersonRepository;
import jesper.summer.service.AccessDeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// AccessDeviceServiceImpl.java
@Service
@RequiredArgsConstructor
@Transactional
public class AccessDeviceServiceImpl implements AccessDeviceService {
    private final AccessDeviceRepository repository;
    private final AccessLogRepository logRepository;
    private final AccessLogServiceImpl logService;
    @Override
    public AccessDevice create(AccessDevice device) {
        System.out.println(device);
        return repository.save(device);
    }
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<String> deviceIds) {
        // 1. 删除关联的通行记录（避免外键冲突）
        int cnt  = deviceIds.size();
        for(String deviceId : deviceIds)
        {
            List<Long> logs = logRepository.findByDeviceId(deviceId);
            for(Long log :logs){
                logService.delete(log);
            }
            // 2. 批量删除设备
            repository.deleteById(deviceId);
        }
        return cnt;
    }
    @Override
    public AccessDevice getById(String deviceId) {
        return repository.findById(deviceId).orElse(null);
    }

    @Override
    public List<AccessDevice> getAll() {
        return repository.findAll();
    }

    @Override
    public AccessDevice update(AccessDevice device) {
        return repository.save(device); // JPA的save会自动识别新增或更新
    }

    @Override
    public void delete(String deviceId) {
        List<Long> logs = logRepository.findByDeviceId(deviceId);
        for(Long log :logs){
            logService.delete(log);
        }
        repository.deleteById(deviceId);
    }
}