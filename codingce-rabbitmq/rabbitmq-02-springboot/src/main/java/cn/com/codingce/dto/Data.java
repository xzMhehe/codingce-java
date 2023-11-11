/**
  * Copyright 2023 json.cn 
  */
package cn.com.codingce.dto;
import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2023-11-11 10:55:35
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private List<Tag> tag;
    private List<TagGroup> tagGroup;
    private String userId;
    private String workspaceId;
    private int isDelete;
    private String type;
    private String applyId;
    private String applyIdPre;
    private int state;
    private Date writeTime;
    private Date reportTime;
    private String reportPdf;
    private Interpretation interpretation;
    private Content content;
    public void setTag(List<Tag> tag) {
         this.tag = tag;
     }
     public List<Tag> getTag() {
         return tag;
     }

    public void setTagGroup(List<TagGroup> tagGroup) {
         this.tagGroup = tagGroup;
     }
     public List<TagGroup> getTagGroup() {
         return tagGroup;
     }

    public void setUserId(String userId) {
         this.userId = userId;
     }
     public String getUserId() {
         return userId;
     }

    public void setWorkspaceId(String workspaceId) {
         this.workspaceId = workspaceId;
     }
     public String getWorkspaceId() {
         return workspaceId;
     }

    public void setIsDelete(int isDelete) {
         this.isDelete = isDelete;
     }
     public int getIsDelete() {
         return isDelete;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setApplyId(String applyId) {
         this.applyId = applyId;
     }
     public String getApplyId() {
         return applyId;
     }

    public void setApplyIdPre(String applyIdPre) {
         this.applyIdPre = applyIdPre;
     }
     public String getApplyIdPre() {
         return applyIdPre;
     }

    public void setState(int state) {
         this.state = state;
     }
     public int getState() {
         return state;
     }

    public void setWriteTime(Date writeTime) {
         this.writeTime = writeTime;
     }
     public Date getWriteTime() {
         return writeTime;
     }

    public void setReportTime(Date reportTime) {
         this.reportTime = reportTime;
     }
     public Date getReportTime() {
         return reportTime;
     }

    public void setReportPdf(String reportPdf) {
         this.reportPdf = reportPdf;
     }
     public String getReportPdf() {
         return reportPdf;
     }

    public void setInterpretation(Interpretation interpretation) {
         this.interpretation = interpretation;
     }
     public Interpretation getInterpretation() {
         return interpretation;
     }

    public void setContent(Content content) {
         this.content = content;
     }
     public Content getContent() {
         return content;
     }

}