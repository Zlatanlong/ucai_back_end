package upctx.qi_back_end.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${spring.file.filePath}")
    private String filePath;
    @Value("${spring.file.urlPath}")
    private String urlPath;

    /**
     * 上传文件 上传成功 则返回地址， 否则返回null
     *
     * @param file
     * @param newDirUrl 新建的的文件夹
     * @return
     */
    public String upload(MultipartFile file, String newDirUrl) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // fileName处理
        String random = String.valueOf(UUID.randomUUID());
        String url = urlPath + newDirUrl + "/" + random + fileName;
        fileName = filePath + newDirUrl + "/" + random + fileName;
        // 文件对象
        File dest = new File(fileName);
        // 创建路径
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 重载
    public String upload(MultipartFile file) {
        return this.upload(file, "");
    }
}
