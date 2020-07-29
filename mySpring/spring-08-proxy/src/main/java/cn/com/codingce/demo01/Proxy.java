package cn.com.codingce.demo01;

public class Proxy implements Rent {

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }


    public void rent() {
        seeHourse();
        host.rent();
        fare();
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
