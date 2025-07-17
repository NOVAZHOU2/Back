package jesper.summer.utils;

import com.baidu.aip.util.Base64Util;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ImageUtil {
    /**
     * 将本地图片文件转换为Base64编码字符串
     * @param filePath 图片文件路径
     * @return Base64编码字符串
     */
    public static String imageToBase64(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int count;
            while ((count = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }

            return Base64Util.encode(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) fis.close();
                if (baos != null) baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}