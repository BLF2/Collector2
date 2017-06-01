package net.blf2.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-5-28.
 */
public class TemplateToData {
    private String itermnName;//名称
    private Boolean requirement = false;//是否必填
    private String itermValueClassification;//类型：text,date，single，multiple，forceKeyValue
    private List<String> itermValueList;//单选和多选的List
    private Map<String,String> itermKeyValues;//键值对


    public String getItermnName() {
        return itermnName;
    }

    public void setItermnName(String itermnName) {
        this.itermnName = itermnName;
    }

    public Boolean getRequirement() {
        return requirement;
    }

    public void setRequirement(Boolean requirement) {
        this.requirement = requirement;
    }

    public String getItermValueClassification() {
        return itermValueClassification;
    }

    public void setItermValueClassification(String itermValueClassification) {
        this.itermValueClassification = itermValueClassification;
    }

    public List<String> getItermValueList() {
        return itermValueList;
    }

    public void setItermValueList(List<String> itermValueList) {
        this.itermValueList = itermValueList;
    }


    public Map<String, String> getItermKeyValues() {
        return itermKeyValues;
    }

    public void setItermKeyValues(Map<String, String> itermKeyValues) {
        this.itermKeyValues = itermKeyValues;
    }
}
