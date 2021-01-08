package cn.com.codingce.testtwo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 掌上编程
 * @Date 2019/10/29 15:56
 */
public class StringToLocalDate {
    public static void main(String[] args) {
        String date1 = "2019-06-13";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date2 = LocalDate.parse(date1, fmt);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(upDateToLocalDateTime(date2));
    }

    /**
     * LocalDate->LocalDateTime
     * @param localDate
     * @return
     */
    public static LocalDateTime upDateToLocalDateTime(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        Instant newInstant = date.toInstant();
        return LocalDateTime.ofInstant(newInstant, zone);
    }

    /**
     * LocalDate->LocalDateTime便捷转换
     * @param startTime
     * @param endTime
     */
    public static void upLocalDateToLocalDateTime(LocalDate startTime, LocalDate endTime) {
        LocalDateTime startLocalDateTime = LocalDateTime.of(startTime, LocalTime.of(0, 0));
        LocalDateTime endLocalDateTime = LocalDateTime.of(endTime, LocalTime.of(23, 59));
    }

}
