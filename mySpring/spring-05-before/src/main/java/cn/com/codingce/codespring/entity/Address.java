package cn.com.codingce.codespring.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Spring 注入集合
 * 你已经看到了如何使用 value 属性来配置基本数据类型和在你的 bean
 * 配置文件中使用<property>标签的 ref 属性来配置对象引用。这两种情况下处理奇异值传递给一个 bean。
 * 现在如果你想传递多个值，如 Java Collection 类型 List、Set、Map 和 Properties，应该怎么做呢。
 * 为了处理这种情况，Spring 提供了四种类型的集合的配置元素，
 *
 *     <list> 它有助于连线，如注入一列值，允许重复。
 *     <set> 它有助于连线一组值，但不能重复。
 *     <map> 它可以用来注入名称-值对的集合，其中名称和值可以是任何类型。
 *     props> 它可以用来注入名称-值对的集合，其中名称和值都是字符串类型。
 *
 * @author 2460798168@qq.com
 * @date 2019/12/25 14:04
 */
public class Address {

    List addressList;
    Set addressSet;
    Map addressMap;
    Properties addressProp;

    /**
     * prints and returns all the elements of the list.
     * @return
     */
    public List getAddressList() {
        System.out.println("List Elements :"  + addressList);
        return addressList;
    }

    public void setAddressList(List addressList) {
        this.addressList = addressList;
    }

    /**
     *  a setter method to set Set
     * @param addressSet
     */
    public void setAddressSet(Set addressSet) {
        this.addressSet = addressSet;
    }

    /**
     * prints and returns all the elements of the Set.
     * @return
     */
    public Set getAddressSet() {
        System.out.println("Set Elements :"  + addressSet);
        return addressSet;
    }

    /**
     * a setter method to set Map
     * @param addressMap
     */
    public void setAddressMap(Map addressMap) {
        this.addressMap = addressMap;
    }

    /**
     * prints and returns all the elements of the Map.
     * @return
     */
    public Map getAddressMap() {
        System.out.println("Map Elements :"  + addressMap);
        return addressMap;
    }

    /**
     * a setter method to set Property
     * @param addressProp
     */
    public void setAddressProp(Properties addressProp) {
        this.addressProp = addressProp;
    }

    /**
     * prints and returns all the elements of the Property.
     * @return
     */
    public Properties getAddressProp() {
        System.out.println("Property Elements :"  + addressProp);
        return addressProp;
    }
}
