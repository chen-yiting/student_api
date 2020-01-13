package com.fh.student.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

public class CopyOSSFile {
    public static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    public static final String accessKeyId = "LTAI4FifZFT2GxiEYQ2EpWjq";
    public static final String accessKeySecret = "8Xq2y73oB8Jdq9nc6wl0Q63Myl7V64";
    public static final String bucketName = "chenyiting";
    public static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public static String CopyOSSFile(MultipartFile photo, String mkdirName){
        //临时文件重命名
        String oldName=photo.getOriginalFilename();//获取老的文件名.后缀
        Long time=System.currentTimeMillis();
        //123.png
        String suffix=oldName.substring(oldName.lastIndexOf("."));
        String newFileName=time+suffix;
        //将文件放入OSS服务器对象中5
        try {
            ossClient.putObject(bucketName,mkdirName+newFileName,new ByteArrayInputStream(photo.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置文件有效时间10年
        Date date = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucketName, mkdirName + newFileName, date).toString();
        //返回相对路径
        return url;
    }
}
