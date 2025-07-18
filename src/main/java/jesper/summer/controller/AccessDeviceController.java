package jesper.summer.controller;

import jesper.summer.dto.AccessDeviceDTO;
import jesper.summer.dto.AccessLogDTO;
import jesper.summer.dto.DeviceDeleteRequest;
import jesper.summer.dto.DeviceQueryRequest;
import jesper.summer.entity.AccessDevice;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.AccessDeviceService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class AccessDeviceController {
    private final AccessDeviceService service;

    @PostMapping
    public AccessDevice create(@RequestBody AccessDevice device) {
        return service.create(device);
    }

    @GetMapping("/by-id")
    public ResponseEntity<AccessDevice> getById(
            @RequestBody DeviceQueryRequest request // 接收包含deviceId的JSON
    ) {
        AccessDevice device = service.getById(request.getDeviceId());
        return ResponseEntity.ok(device);
    }
    @DeleteMapping("/batch")
    public ResponseEntity<Map<String, Object>> batchDeleteDevices(
            @RequestBody DeviceBatchDeleteRequest request  // 封装请求体
    ) throws BusinessException {
        if (request.getDeviceIds() == null || request.getDeviceIds().isEmpty()) {
            throw new BusinessException("设备ID列表不能为空");
        }
        int deletedCount = service.batchDelete(request.getDeviceIds());

        return ResponseEntity.ok(Map.of(
                "code", HttpStatus.OK.value(),
                "message", "成功删除 " + deletedCount + " 台设备",
                "data", Map.of("deviceIds", request.getDeviceIds()),
                "timestamp", LocalDateTime.now()
        ));
    }
    @GetMapping
    public List<AccessDevice> getAll() {
        return service.getAll();
    }

    @PutMapping
    public AccessDevice update(@RequestBody AccessDevice device) {
        return service.update(device);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete(
            @RequestBody DeviceDeleteRequest request // 接收包含deviceId的JSON
    ) {
        System.out.println(request.getDeviceId());
        service.delete(request.getDeviceId());

        return ResponseEntity.ok(Map.of(
                "code", 200,
                "message", "设备删除成功",
                "deviceId", request.getDeviceId(),
                "timestamp", LocalDateTime.now()
        ));
    }
    @Data
    public static class DeviceBatchDeleteRequest {
        @NotNull(message = "deviceIds不能为空")
        @Size(min = 1, max = 1000, message = "单次最多删除1000台设备")
        private List<String> deviceIds;  // 支持字符串型ID（如UUID）
    }
}