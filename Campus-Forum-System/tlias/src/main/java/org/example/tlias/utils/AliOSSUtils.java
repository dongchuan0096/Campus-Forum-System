package org.example.tlias.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {

/*   @Value("${aliyun.oos.endpoint}")
    private String endpoint;
    @Value("${aliyun.oos.accessKeyId}")
    private String accessKeyId ;
    @Value("${aliyun.oos.accessKeySecret}")
    private String accessKeySecret ;
    @Value("${aliyun.oos.bucketName}")
    private String bucketName;*/

    /**
     * 实现上传图片到OSS
     */
    @Autowired
    AliOSSProperties aliOSSProperties;
    public String upload(MultipartFile file) throws IOException {

        String endpoint=aliOSSProperties.getEndpoint();
        String accessKeyId=aliOSSProperties.getAccessKeyId();
        String accessKeySecret= aliOSSProperties.getAccessKeySecret();
        String bucketName= aliOSSProperties.getBucketName();
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();
        System.out.println("////////////////////////////////////////////");
        // 避免文件覆盖,起别名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
