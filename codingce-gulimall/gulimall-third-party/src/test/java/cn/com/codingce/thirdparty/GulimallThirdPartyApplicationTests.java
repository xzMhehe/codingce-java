package cn.com.codingce.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void picOSS() throws FileNotFoundException {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "endpoint";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "accessKeyId";
//        String accessKeySecret = "accessKeySecret";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\comment_bg.png");
//        ossClient.putObject("codingce-product", "comment_bg.png", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传完成...");
    }

    @Test
    void testOssUpload() throws FileNotFoundException {
        // 上传文件流。
        InputStream inputStream = new FileInputStream("D:\\a.png");
        ossClient.putObject("codingce-product", "a.png", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传完成...");
    }

}
