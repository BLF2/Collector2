package net.blf2.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blf2 on 17-4-11.
 * 生成表单的信息
 */
public class ItermInfo {
    public static final String ITERM_CONSTRAINT = "ItermConstraint";//项目约束
    public static final String ALLOW_REPEAT = "AllowRepeat";//允许重复
    private Map<String,Boolean>ItermConstraintMap = new HashMap<>();//项目约束map

    public Map<String, Boolean> getItermConstraintMap() {
        return ItermConstraintMap;
    }

    public void setItermConstraintMap(Map<String, Boolean> itermConstraintMap) {
        ItermConstraintMap = itermConstraintMap;
    }
}
