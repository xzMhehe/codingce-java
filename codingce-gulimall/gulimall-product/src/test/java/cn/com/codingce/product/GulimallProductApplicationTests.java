package cn.com.codingce.product;

import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("世界第一");
        brandEntity.setName("华为");

        brandService.save(brandEntity);

    }
}
