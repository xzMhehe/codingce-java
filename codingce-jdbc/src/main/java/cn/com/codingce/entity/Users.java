package cn.com.codingce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int pid;

    private int sno;

    private String sname;

    private int sage;

    private String saddress;

    private String spwd;

    private Date sbirth;
}
