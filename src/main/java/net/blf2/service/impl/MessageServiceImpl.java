package net.blf2.service.impl;

import net.blf2.dao.IMessageDao;
import net.blf2.entity.MessageInfo;
import net.blf2.exception.DeleteException;
import net.blf2.exception.InsertException;
import net.blf2.exception.QueryException;
import net.blf2.exception.UpdateException;
import net.blf2.service.IMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-5-15.
 * IMessageService的实现类
 */
@Service("MessageServiceImpl")
@Transactional(rollbackFor = RuntimeException.class)
public class MessageServiceImpl implements IMessageService{
    @Resource
    private IMessageDao messageDao;
    @Override
    public boolean insertMessageInfo(MessageInfo messageInfo) {
        try {
            messageDao.insertMessageInfo(messageInfo);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new InsertException(this);
        }
        return true;
    }

    @Override
    public boolean insertMessageInfos(List<MessageInfo> messageInfos) {
        try {
            messageDao.insertMessageInfos(messageInfos);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new InsertException(this);
        }
        return true;
    }

    @Override
    public boolean deleteMessageInfoByMessageId(String messageId) {
        try {
            messageDao.deleteMessageInfoByMessageId(messageId);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new DeleteException(this);
        }
        return true;
    }

    @Override
    public boolean deleteMessageInfosByMessageIds(List<String> messageIds) {
        try {
            messageDao.deleteMessageInfosByMessageIds(messageIds);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new DeleteException(this);
        }
        return true;
    }

    @Override
    public boolean updateMessageInfo(MessageInfo messageInfo) {
        try {
            messageDao.updateMessageInfo(messageInfo);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new UpdateException(this);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = RuntimeException.class)
    public List<MessageInfo> queryMessageInfoAll() {
        try {
            return messageDao.queryMessageInfoAll();
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new QueryException(this);
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = RuntimeException.class)
    public List<MessageInfo> queryMessageInfosByRecieverId(String recieverId) {
        try {
            return messageDao.queryMessageInfosByRecieverId(recieverId);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new QueryException(this);
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = RuntimeException.class)
    public List<MessageInfo> queryMessageInfosBySenderId(String senderId) {
        try {
            return messageDao.queryMessageInfosBySenderId(senderId);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new QueryException(this);
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = RuntimeException.class)
    public MessageInfo queryMessageInfoByMessageId(String messageId) {
        try {
           return messageDao.queryMessageInfoByMessageId(messageId);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new QueryException(this);
        }
    }

    @Override
    public boolean updateMessageStatus(String messageId, Integer currentStatus) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessageId(messageId);
        messageInfo.setCurrentStatus(currentStatus);
        try {
            messageDao.updateMessageInfo(messageInfo);
        }catch (RuntimeException re){
            re.printStackTrace();
            throw new UpdateException(this);
        }
        return true;
    }
}
