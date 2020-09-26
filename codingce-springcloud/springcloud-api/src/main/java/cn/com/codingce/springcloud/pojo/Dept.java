package cn.com.codingce.springcloud.pojo;

import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xzMa
 * Dept实体类 orm  类表关系映射
 */
@Accessors(chain = true)  //链式写法
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String db_source;

    /**
     * Dept dept = new Dept();
     * dept.setDeptNo("").setDeptyName().....  都行
     */

    public Dept() {
    }

    public Dept(Long deptno, String dname, String db_source) {
        this.deptno = deptno;
        this.dname = dname;
        this.db_source = db_source;
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDb_source() {
        return db_source;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", db_source='" + db_source + '\'' +
                '}';
    }
}
