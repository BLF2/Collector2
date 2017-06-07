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
    private String messageContent;//内容

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

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageInfo that = (MessageInfo) o;

        if (!messageId.equals(that.messageId)) return false;
        if (!senderId.equals(that.senderId)) return false;
        if (!recieverId.equals(that.recieverId)) return false;
        if (sendDateTime != null ? !sendDateTime.equals(that.sendDateTime) : that.sendDateTime != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (currentStatus != null ? !currentStatus.equals(that.currentStatus) : that.currentStatus != null)
            return false;
        return messageContent != null ? messageContent.equals(that.messageContent) : that.messageContent == null;
    }

    @Override
    public int hashCode() {
        int result = messageId.hashCode();
        result = 31 * result + senderId.hashCode();
        result = 31 * result + recieverId.hashCode();
        result = 31 * result + (sendDateTime != null ? sendDateTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        return result;
    }
}
