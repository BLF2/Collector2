package net.blf2.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by blf2 on 17-5-25.
 */
public class InfoTemplateForm {
    private String templateId;
    private List<InfoTemplate>infoTemplateList = new LinkedList<>();
    private String introductionString;//介绍
    private String endDateTime;//结束时间
    private String createDateTime;//创建时间
    private String createNum;//创建者Id

    public String getIntroductionString() {
        return introductionString;
    }

    public void setIntroductionString(String introductionString) {
        this.introductionString = introductionString;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getCreateNum() {
        return createNum;
    }

    public void setCreateNum(String createNum) {
        this.createNum = createNum;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<InfoTemplate> getInfoTemplateList() {
        return infoTemplateList;
    }

    public void setInfoTemplateList(List<InfoTemplate> infoTemplateList) {
        this.infoTemplateList = infoTemplateList;
    }
}
