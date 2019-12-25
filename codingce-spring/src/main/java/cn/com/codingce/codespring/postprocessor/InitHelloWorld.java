package cn.com.codingce.codespring.postprocessor;

import cn.com.codingce.codespring.entity.HelloWorld;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/25 9:34
 */
public class InitHelloWorld implements BeanPostProcessor {

    /**
     * 前置处理器
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if (beanClass == HelloWorld.class) {
            System.out.println("bean 对象初始化之前······" + beanName);
        }
        return bean;
    }

    /**
     * 后置处理器 --- 此处具体的实现用的是Java中的动态代理
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        // 为当前 bean 对象注册监控代理对象，负责增强 bean 对象方法的能力
        Class beanClass = bean.getClass();
        if (beanClass == HelloWorld.class) {
            System.out.println("AfterInitialization :(bean 对象初始化之后) " + beanName);
        }
        return bean;
    }
}
