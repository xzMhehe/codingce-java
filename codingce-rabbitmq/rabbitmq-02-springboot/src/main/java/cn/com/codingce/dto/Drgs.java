/**
  * Copyright 2023 json.cn 
  */
package cn.com.codingce.dto;
import java.util.List;

/**
 * Auto-generated: 2023-11-11 10:55:35
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Drgs {

    private String name;
    private List<Item> item;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setItem(List<Item> item) {
         this.item = item;
     }
     public List<Item> getItem() {
         return item;
     }

}