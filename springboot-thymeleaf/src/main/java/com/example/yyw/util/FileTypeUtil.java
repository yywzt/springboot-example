package com.example.yyw.util;

import com.example.yyw.enums.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/21 11:13 获取文件头
 */
@Slf4j
public class FileTypeUtil {

    public static final Integer IMG_FILE_SIZE = 2;

    /**
     * jpg格式 返回 FFD8FF开头字符串
     * png格式 返回 89504E47开头字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String getFileType(InputStream is) throws IOException {
        byte[] src = new byte[28];
        is.read(src, 0, 28);
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 校验文件格式大小
     *
     * @param file
     * @param fileTypeEnums 文件头 FileTypeEnum
     * @param size          单位MB -1不限制大小
     * @return
     */
    public static boolean checkFileTypeAndSize(MultipartFile file, List<FileTypeEnum> fileTypeEnums, Integer size) {
        return checkFileType(file, fileTypeEnums) && checkFileSize(file, size);
    }

    public static boolean checkFileType(MultipartFile file, List<FileTypeEnum> fileTypeEnums){
        String fileType = "";
        try {
            fileType = getFileType(file.getInputStream());
        } catch (IOException e) {
            log.error("获取文件头error: {}", e.getMessage());
        }
        for (FileTypeEnum fileTypeEnum : fileTypeEnums) {
            if (fileType.startsWith(fileTypeEnum.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param file
     * @param size 单位MB -1不限制大小
     * @return
     */
    private static boolean checkFileSize(MultipartFile file, Integer size) {
        if(size.equals(-1) || (file != null && file.getSize() <= size * 1024 * 1024)){
            return true;
        }
        return false;
    }

}
