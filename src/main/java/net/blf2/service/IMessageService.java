package net.blf2.service;

import net.blf2.entity.MessageInfo;

import java.util.List;

/**
 * Created by blf2 on 17-5-15.
 * 消息service
 */
public interface IMessageService {
    boolean insertMessageInfo(MessageInfo messageInfo);//增加一条消息
    boolean insertMessageInfos(List<MessageInfo> messageInfos);//增加多条消息
    boolean deleteMessageInfoByMessageId(String messageId);//根据消息id删除一条消息
    boolean deleteMessageInfosByMessageIds(List<String> messageIds);//根据多个Id删除对应的消息
    boolean updateMessageInfo(MessageInfo messageInfo);//更新消息
    List<MessageInfo> queryMessageInfoAll();//查询所有消息
    List<MessageInfo> queryMessageInfosByRecieverId(String recieverId);//根据接受者Id查询所有消息
    List<MessageInfo> queryMessageInfosBySenderId(String senderId);//根据发送者Id查询所有消息
    MessageInfo queryMessageInfoByMessageId(String messageId);//根据消息Id查询消息
    boolean updateMessageStatus(String messageId,Integer currentStatus);//更新消息状态
}
