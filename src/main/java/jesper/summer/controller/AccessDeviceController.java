package jesper.summer.controller;

import jesper.summer.dto.AccessDeviceDTO;
import jesper.summer.dto.AccessLogDTO;
import jesper.summer.dto.DeviceDeleteRequest;
import jesper.summer.dto.DeviceQueryRequest;
import jesper.summer.entity.AccessDevice;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.AccessDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}