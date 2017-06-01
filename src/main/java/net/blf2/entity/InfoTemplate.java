package net.blf2.entity;

/**
 * Created by blf2 on 17-5-25.
 * model for 信息采集模板
 */
public class InfoTemplate {
    private String itermName;//项目名
    private String itermCondition;//项目约束
    private String valueCondition;//值约束
    private String conditionValue;//值内容

    public String getItermName() {
        return itermName;
    }

    public void setItermName(String itermName) {
        this.itermName = itermName;
    }

    public String getItermCondition() {
        return itermCondition;
    }

    public void setItermCondition(String itermCondition) {
        this.itermCondition = itermCondition;
    }

    public String getValueCondition() {
        return valueCondition;
    }

    public void setValueCondition(String valueCondition) {
        this.valueCondition = valueCondition;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }
}
