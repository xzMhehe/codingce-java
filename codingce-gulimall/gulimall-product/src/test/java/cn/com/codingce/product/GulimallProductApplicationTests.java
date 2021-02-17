package cn.com.codingce.product;

import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.service.BrandService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Autowired
    OSSClient ossClient;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("世界第一");
        brandEntity.setName("华为");

        brandService.save(brandEntity);

    }

    @Test
    void picOSS() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "endpoint";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "accessKeyId";
        String accessKeySecret = "accessKeySecret";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("D:\\comment_bg.png");
        ossClient.putObject("codingce-product", "comment_bg.png", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传完成...");
    }

    @Test
    void testOssUpload() {

    }

}
