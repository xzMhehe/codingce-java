package cn.campsg.java.experiment;

/**
 * @author Zhao Jiaxin
 */
public class MainClass {

    public static void main(String[] args) {
        System.out.println("图书馆规定最晚归还日期:" + DateUtils.getReturnDate());
        if (DateUtils.getReturnDate(10) != null) {
            System.out.println("书籍实际归还日期:" + DateUtils.getReturnDate(10));
        }
        else {
            System.out.println("借阅时间过长");
        }
    }

}
