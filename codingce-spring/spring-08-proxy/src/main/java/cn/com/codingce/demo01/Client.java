package cn.com.codingce.demo01;

public class Client {

     public static void main(String[] args) {
        Host host = new Host();
        host.rent();
        //代理模式 中介帮房东租房子 但是呢 代理一般都有一些附属操作
         Proxy proxy = new Proxy(host);
         proxy.rent();

     }

}
