package cn.com.codingce.codespring.entity;

/**
 * Spring Bean 定义继承
 *
 * @author 2460798168@qq.com
 * @date 2019/12/25 10:21
 */
public class Inherit {

    private String message1;
    private String message2;
    private String message3;

    public void setMessage1(String message){
        this.message1  = message;
    }

    public void setMessage2(String message){
        this.message2  = message;
    }

    public void setMessage3(String message){
        this.message3  = message;
    }

    public void getMessage1(){
        System.out.println("India Message1 : " + message1);
    }

    public void getMessage2(){
        System.out.println("India Message2 : " + message2);
    }

    public void getMessage3(){
        System.out.println("India Message3 : " + message3);
    }

    public void init(){
        System.out.println("BeanIndia is going through init.");
    }
    public void destroy(){
        System.out.println("BeanIndia will destroy now.");
    }
}
