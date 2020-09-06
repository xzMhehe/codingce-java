package cn.com.codingce.pojo;

import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xzMa
 * Dept实体类 orm  类表关系映射
 */
@Accessors  //链式写法
public class Dept implements Serializable {
    private Long depton;
    private String dname;
    private String db_source;

    /**
     * Dept dept = new Dept();
     * dept.setDeptNo("").setDeptyName().....  都行
     */

    public Dept() {
    }

    public Dept(String dname) {
        this.dname = dname;
    }

    public Dept(Long depton, String dname) {
        this.depton = depton;
        this.dname = dname;
    }

    public Long getDepton() {
        return depton;
    }

    public void setDepton(Long depton) {
        this.depton = depton;
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
                "depton=" + depton +
                ", dname='" + dname + '\'' +
                '}';
    }
}
