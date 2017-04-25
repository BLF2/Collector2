package net.blf2.entity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-4-11.
 * 普通键值对类
 */
public class PrimaryKeyValue extends ItermInfo{
    public static final String REQUIRED_INFORMATION = "RequiredInformation";//必填
    public static final String REDIO_INFORMATION = "Redio_Information";//单选
    public static final String CHECK_BOX = "CheckBox";//复选
    public static final String TEXT_AREA = "Text_Area";//文本框

    private String itermName;//项目名
    private Map<String,List<String>>valueConstraintMap = new HashMap<>();//值约束，包括单选和复选及其内容

    public String getItermName() {
        return itermName;
    }

    public void setItermName(String itermName) {
        this.itermName = itermName;
    }

    public Map<String, List<String>> getValueConstraintMap() {
        return valueConstraintMap;
    }

    public void setValueConstraintMap(Map<String, List<String>> valueConstraintMap) {
        this.valueConstraintMap = valueConstraintMap;
    }
}
