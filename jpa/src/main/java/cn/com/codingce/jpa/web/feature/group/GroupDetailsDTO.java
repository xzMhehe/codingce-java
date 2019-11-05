package cn.com.codingce.jpa.web.feature.group;

import java.time.LocalDateTime;

/**
 * 分组数据
 * @Date 2019/10/23 13:40
 */
public class GroupDetailsDTO {

    /**
     * 分组pid
     */
    private String pid;
    /**
     * 组织机构pid
     */
    private String orgPid;
    /**
     * 用户pid
     */
    private String userPid;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 分组介绍
     */
    private String groupDesc;

    /**
     * 人数
     */
    private Long userNumber;

    public GroupDetailsDTO() {
    }

    public GroupDetailsDTO(String pid,
                           String orgPid,
                           String userPid,
                           String groupName,
                           LocalDateTime createTime,
                           String groupDesc) {
        this.pid = pid;
        this.orgPid = orgPid;
        this.userPid = userPid;
        this.groupName = groupName;
        this.createTime = createTime;
        this.groupDesc = groupDesc;
    }

    public GroupDetailsDTO(String pid,
                           String orgPid,
                           String userPid,
                           String groupName,
                           LocalDateTime createTime,
                           String groupDesc,
                           Long userNumber) {
        this.pid = pid;
        this.orgPid = orgPid;
        this.userPid = userPid;
        this.groupName = groupName;
        this.createTime = createTime;
        this.groupDesc = groupDesc;
        this.userNumber = userNumber;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    public String getUserPid() {
        return userPid;
    }

    public void setUserPid(String userPid) {
        this.userPid = userPid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "GroupDetailsDTO{" +
                "pid='" + pid + '\'' +
                ", orgPid='" + orgPid + '\'' +
                ", userPid='" + userPid + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", userNumber=" + userNumber +
                '}';
    }

}
