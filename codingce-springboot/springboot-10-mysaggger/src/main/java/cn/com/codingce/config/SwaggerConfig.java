package cn.com.codingce.config;

import cn.com.codingce.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C");
    }

    @Bean
    public Docket docket4() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("D");
    }

    @Bean //配置docket以配置Swagger具体参数  链式编程
    public Docket docket(Environment environment) {
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");

        //获取项目环境    Environment environment.acceptsProfiles(profiles);判断是否处于自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                //用的自己的             return new Docket(DocumentationType.SWAGGER_2); 默认
                .apiInfo(apiInfo())
                //enable    是否启动Swagger, 如果false则Swagger不能再浏览器中访问
                //.enable(false)
                .groupName("掌上编程")
                .enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫面接口的方式
                //basePackage   指定要扫描的包      .apis(RequestHandlerSelectors.basePackage("cn.com.codingce.controller"))
                //any() 扫描全部
                //no() 都不扫描
                //withClassAnnotation   :扫描类上的注解, 参数是一个注解反射的对象
                //withMethodAnnotation  :扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("cn.com.codingce.controller"))
                //paths() 过滤路径
                //.paths(PathSelectors.ant("/codingce/**"))
                .build();//build工厂模式
    }

    //配置swaagger信息
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("马鑫泽", "https://i.codingce.com.cn", "codingce@gmail.com");
        return new ApiInfo("掌上编程",
                "即使再小的帆也能远航",
                "1.0",
                "https://i.codingce.com.cn",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }




}
