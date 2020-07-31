package cn.com.codingce.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会我们会用这个类, 自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    public Rent rent;

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    ////生成代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    //处理代理的实例, 并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质, 就是使用反射机制来实现
        seeHourse();
        Object result = method.invoke(rent, args);
        return result;
    }

    //看房
    public void seeHourse() {
        System.out.println("中介带你看房");
    }

    //合同
    public void heTong() {
        System.out.println("签租赁合同");
    }

    //中介费
    public void fare() {
        System.out.println("收中介费");
    }
}
