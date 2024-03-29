//package com.bsh.lable.framework.config;
//
//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author ma
// */
//@Configuration
//public class NacosRegisterConfig {
//
//    @Value("${server.port}")
//    private int serverPort;
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @NacosInjected
//    private NamingService namingService;
//
//    @PostConstruct
//    public void registerInstance() throws NacosException {
//        namingService.registerInstance(applicationName, "127.0.0.1", serverPort, "DEFAULT");
//    }
//
//}
//
