package jesper.summer.controller;

import jesper.summer.dto.AccessLogCreateDTO;
import jesper.summer.dto.AccessLogDTO;
import jesper.summer.entity.AccessLog;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.AccessLogService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @Data
    public static class LogDeleteRequest {
        @NotNull(message = "日志ID不能为空")
        private Long logId;
    }

}