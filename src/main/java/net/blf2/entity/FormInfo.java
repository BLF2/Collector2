package net.blf2.entity;

import java.util.List;

/**
 * Created by blf2 on 17-4-11.
 * 表单综合信息
 */
public class FormInfo {
    private List<ItermInfo> itermInfoList;//项目列表
    private String introductionString;//介绍
    private boolean noteString;//备注框
    private String startDateTime;//开始时间
    private String endDateTime;//结束时间

    public List<ItermInfo> getItermInfoList() {
        return itermInfoList;
    }

    public void setItermInfoList(List<ItermInfo> itermInfoList) {
        this.itermInfoList = itermInfoList;
    }

    public String getIntroductionString() {
        return introductionString;
    }

    public void setIntroductionString(String introductionString) {
        this.introductionString = introductionString;
    }

    public boolean isNoteString() {
        return noteString;
    }

    public void setNoteString(boolean noteString) {
        this.noteString = noteString;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
