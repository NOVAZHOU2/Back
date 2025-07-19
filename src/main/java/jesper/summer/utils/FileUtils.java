package jesper.summer.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/api")
public class FileUtils {

    private static final String UPLOAD_DIR = "C:\\Users\\lENOVO\\Desktop\\shixun\\project\\demo\\Back\\src\\main\\resources\\upload";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        // 检查上传目录是否存在，不存在则创建
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 验证文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("上传的文件为空");
        }

        // 获取原始文件名的扩展名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 生成唯一文件名
        String filename = UUID.randomUUID() + fileExtension;
        Path path = uploadPath.resolve(filename);

        // 保存文件
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // 返回相对路径或URL，而不是绝对路径
        return ResponseEntity.ok("/upload/" + filename);
    }
}
