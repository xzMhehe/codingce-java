import java.util.Calendar;

public class TestFour {
    public static void main(String[] args) {
        // 其日历字段已由当前日期和时间初始化：
        // 子类对象
        Calendar rightNow = Calendar.getInstance();
        // 获取年
        int year = rightNow.get(Calendar.YEAR);
        // 获取月
        int month = rightNow.get(Calendar.MONTH);
        // 获取日
        int date = rightNow.get(Calendar.DATE);
        //获取几点
        int hour=rightNow.get(Calendar.HOUR_OF_DAY);
        //获取上午下午
        int moa=rightNow.get(Calendar.AM_PM);
        if(moa==1)
            System.out.println("下午");
        else
            System.out.println("上午");
        rightNow.add(Calendar.DATE,100);
        int year1 = rightNow.get(Calendar.YEAR);
        int date1 = rightNow.get(Calendar.DATE);
        int month1 = rightNow.get(Calendar.MONTH);
        System.out.println(year1 + "年" + month1 + "月" + date1 + "日");
    }
}
