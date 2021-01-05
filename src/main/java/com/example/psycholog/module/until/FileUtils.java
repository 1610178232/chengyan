package com.example.psycholog.module.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class FileUtils {

    public static R uploadFileUt(MultipartFile file) {
        String filepath="D:\\fileUpload";
        String localUrl= "http://192.168.1.139:5488";

        if (file.isEmpty()) {
            return R.error("文件获取为空");
        }
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //文件大小
        int size = (int) file.getSize();
        System.out.println("文件名：" + fileName + "；文件大小：" + size);

        String imgName = UUID.randomUUID().toString();
        //获取文件名后缀，重新生成文件名
        String newFileName = imgName + "." + FileUtils.getExtensionName(fileName);
        //获取日期
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        //文件目录
        String path = filepath + File.separator + year + File.separator + month +File.separator + day ;
        //创建文件目录
        File dest = new File(path + File.separator + newFileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        String fileReName = localUrl + "/" + year + "/" + month + "/" + day + "/" + newFileName;
        try {
            file.transferTo(dest);//如压缩上传失败，则使用原生的上传压缩
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("上传成功").put("filePath", fileReName);

    }
    /**获取文件后缀名*/
    public static String getExtensionName(String filename) {
        if((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
