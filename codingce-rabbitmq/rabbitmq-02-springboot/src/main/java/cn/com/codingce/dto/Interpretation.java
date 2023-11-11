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
public class Interpretation {

    private int estimateScore;
    private String conclusion;
    private String riskLevel;
    private String explanation;
    private String advice;
    private String remark;
    private List<AbnormalFactors> abnormalFactors;
    private List<Drgs> drgs;
    private List<String> noIndicatorData;
    public void setEstimateScore(int estimateScore) {
         this.estimateScore = estimateScore;
     }
     public int getEstimateScore() {
         return estimateScore;
     }

    public void setConclusion(String conclusion) {
         this.conclusion = conclusion;
     }
     public String getConclusion() {
         return conclusion;
     }

    public void setRiskLevel(String riskLevel) {
         this.riskLevel = riskLevel;
     }
     public String getRiskLevel() {
         return riskLevel;
     }

    public void setExplanation(String explanation) {
         this.explanation = explanation;
     }
     public String getExplanation() {
         return explanation;
     }

    public void setAdvice(String advice) {
         this.advice = advice;
     }
     public String getAdvice() {
         return advice;
     }

    public void setRemark(String remark) {
         this.remark = remark;
     }
     public String getRemark() {
         return remark;
     }

    public void setAbnormalFactors(List<AbnormalFactors> abnormalFactors) {
         this.abnormalFactors = abnormalFactors;
     }
     public List<AbnormalFactors> getAbnormalFactors() {
         return abnormalFactors;
     }

    public void setDrgs(List<Drgs> drgs) {
         this.drgs = drgs;
     }
     public List<Drgs> getDrgs() {
         return drgs;
     }

    public void setNoIndicatorData(List<String> noIndicatorData) {
         this.noIndicatorData = noIndicatorData;
     }
     public List<String> getNoIndicatorData() {
         return noIndicatorData;
     }

}