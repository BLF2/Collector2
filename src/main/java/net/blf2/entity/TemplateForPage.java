package net.blf2.entity;

import java.util.List;

/**
 * Created by blf2 on 17-5-28.
 */
public class TemplateForPage {
    private String templateId;//模板Id
    private String introductionString;
    private List<TemplateToData> templateToDataList;//模板

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<TemplateToData> getTemplateToDataList() {
        return templateToDataList;
    }

    public void setTemplateToDataList(List<TemplateToData> templateToDataList) {
        this.templateToDataList = templateToDataList;
    }

    public String getIntroductionString() {
        return introductionString;
    }

    public void setIntroductionString(String introductionString) {
        this.introductionString = introductionString;
    }
}
