package net.blf2.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blf2 on 17-4-11.
 * 强制键值对
 */
public class ForceKeyValue extends ItermInfo{
    private Map<String,Object>classificationKeyValuesMap = new HashMap<>(); //强制键值对

    public Map<String, Object> getClassificationKeyValuesMap() {
        return classificationKeyValuesMap;
    }

    public void setClassificationKeyValuesMap(Map<String, Object> classificationKeyValuesMap) {
        this.classificationKeyValuesMap = classificationKeyValuesMap;
    }
}
