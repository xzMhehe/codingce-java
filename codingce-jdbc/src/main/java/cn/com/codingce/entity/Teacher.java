package cn.com.codingce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private int pid;

    private String tname;

    private String tpwd;

}
