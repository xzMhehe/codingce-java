package cn.com.codingce.iocannotation;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 11:27
 */
public class User {

    private String username;
    private String password;
    private Float balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @MyAnnotation({"掌上编程公众号","123456","4567"})
    public User() {
    }

    public User(String username, String password, Float balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
