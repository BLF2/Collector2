package net.blf2.entity;

/**
 * Created by blf2 on 17-5-13.
 * 个人消息，教师或者班长给出需要填写的信息的时候，用户可以去自己的消息列表查到需要填写的信息
 */
public class MessageInfo {
    private String messageId;//消息Id
    private String senderId;//发送者Id
    private String recieverId;//接受者Id
    private String sendDateTime;//发送时间
    private String startTime;//开始时间
    private String endTime;//结束时间
    private Integer currentStatus;//当前状态

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }
}
