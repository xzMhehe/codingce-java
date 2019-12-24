package cn.com.codingce.codespring;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/24 11:52
 */
public class HelloWorld {

    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Your Message : " + message);
    }

}
