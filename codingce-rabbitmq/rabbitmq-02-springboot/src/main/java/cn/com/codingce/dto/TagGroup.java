package cn.com.codingce.dto;

import lombok.Data;

@Data
public class TagGroup {

    private String groupId;
    private String groupName;
    public void setGroupId(String groupId) {
         this.groupId = groupId;
     }
     public String getGroupId() {
         return groupId;
     }

    public void setGroupName(String groupName) {
         this.groupName = groupName;
     }
     public String getGroupName() {
         return groupName;
     }

}