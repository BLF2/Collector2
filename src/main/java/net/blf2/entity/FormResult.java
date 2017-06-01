package net.blf2.entity;


/**
 * Created by blf2 on 17-4-13.
 * 从表单中得到的值
 */
public class FormResult {
    private String formResultMap;//名字和结果的映射
    private String templateId;
    private String submiterId;
    private String submitDateTime;

    public String getFormResultMap() {
        return formResultMap;
    }

    public void setFormResultMap(String formResultMap) {
        this.formResultMap = formResultMap;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSubmiterId() {
        return submiterId;
    }

    public void setSubmiterId(String submiterId) {
        this.submiterId = submiterId;
    }

    public String getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(String submitDateTime) {
        this.submitDateTime = submitDateTime;
    }
}
