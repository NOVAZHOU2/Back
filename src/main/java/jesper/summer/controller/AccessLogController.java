package jesper.summer.controller;

import jesper.summer.entity.AccessLog;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.AccessLogService;
import jesper.summer.vo.DeviceAccessCountVO;
import jesper.summer.vo.VisitorRatio;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class AccessLogController {
    private final AccessLogService service;

    @PostMapping
    public AccessLog create(@RequestBody AccessLog log) {
        System.out.println(log);
        return service.create(log);
    }
    @PostMapping("/batch")
    public ResponseEntity<Map<String, Object>> batchDeleteLogs(
            @RequestBody LogBatchDeleteRequest request  // 封装为对象
    ) throws BusinessException {
        if (request.getLogIds() == null || request.getLogIds().isEmpty()) {
            throw new BusinessException("日志ID列表不能为空");
        }
        int deletedCount = service.batchDelete(request.getLogIds());

        return ResponseEntity.ok(Map.of(
                "code", HttpStatus.OK.value(),
                "message", "成功删除 " + deletedCount + " 条日志",
                "logIds", request.getLogIds(),
                "timestamp", LocalDateTime.now()
        ));
    }
    @GetMapping
    public List<AccessLog> getAll() {
        return service.getAll();
    }

    @PostMapping("/delete")  // 路径调整为POST语义
    public ResponseEntity<String> deleteLog(
            @RequestBody LogDeleteRequest request  // 接收包含logId的JSON
    ) {
        String ans = service.delete(request.getLogId());
        return ResponseEntity.ok(ans);
    }


    @GetMapping("/device/in")
    public ResponseEntity<List<DeviceAccessCountVO>> getDeviceAccessCount(){
        return ResponseEntity.ok(service.getDeviceAccessCount());
    }

    @GetMapping("/device/stop")
    public ResponseEntity<List<DeviceAccessCountVO>> getDeviceAccessCount0(){
        return ResponseEntity.ok(service.getDeviceAccessCountStop());
    }

    @GetMapping("/Person")
    public ResponseEntity<List<VisitorRatio>> getPersonCount(){
        return ResponseEntity.ok(service.getPersonCount());
    }



    @Data
    public static class LogDeleteRequest {
        @NotNull(message = "日志ID不能为空")
        private Long logId;
    }
    @Data  // Lombok注解，自动生成getter/setter
    public static class LogBatchDeleteRequest {
        @NotNull(message = "logIds不能为空")
        private List<Long> logIds;
    }





}