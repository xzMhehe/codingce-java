package org.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author org
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int pid;

    private int sno;

    private String sname;

    private int sage;

    private String saddress;

}
