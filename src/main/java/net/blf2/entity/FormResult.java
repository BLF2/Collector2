package net.blf2.entity;

import java.util.Map;

/**
 * Created by blf2 on 17-4-13.
 * 从表单中得到的值
 */
public class FormResult {
    private Map<String,Object> formResultMap;//名字和结果的映射

    public Map<String, Object> getFormResultMap() {
        return formResultMap;
    }

    public void setFormResultMap(Map<String, Object> formResultMap) {
        this.formResultMap = formResultMap;
    }
}
