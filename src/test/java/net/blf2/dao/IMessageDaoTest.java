package net.blf2.dao;

import net.blf2.entity.MessageInfo;
import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by blf2 on 17-5-13.
 * 测试消息数据库访问
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="net.blf2.dao")
public class IMessageDaoTest {

    @Resource
    private IMessageDao messageDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IUserRoleDao userRoleDao;

    private static String className1 = "软件1303";
    private static String className2 = "软件1302";
    private static String className3 = "会计1501";
    @Test
    public void testIMessageDao(){
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserRoleId(UUID.randomUUID().toString());
        userRoleInfo.setUserRoleName("admin");
        userRoleDao.insertUserRoleInfo(userRoleInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserName("孟宪厚");
        userInfo1.setUserNum("13110572081");
        userInfo1.setUserMajorityClass(className1);
        userInfo1.setUserPhone("15800499264");
        userInfo1.setUserPswd("mxh19940822");
        userInfo1.setUserRoleInfo(userRoleInfo);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserName("陈海波");
        userInfo2.setUserNum("13110572042");
        userInfo2.setUserMajorityClass(className2);
        userInfo2.setUserPhone("18753377309");
        userInfo2.setUserPswd("hypo2526");
        userInfo2.setUserRoleInfo(userRoleInfo);

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setUserName("任梦笛");
        userInfo3.setUserNum("15121404037");
        userInfo3.setUserMajorityClass(className3);
        userInfo3.setUserPhone("18753377310");
        userInfo3.setUserPswd("rmd");
        userInfo3.setUserRoleInfo(userRoleInfo);

        userDao.insertUserInfo(userInfo1);
        userDao.insertUserInfo(userInfo2);
        userDao.insertUserInfo(userInfo3);

        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setMessageId(UUID.randomUUID().toString());
        messageInfo1.setRecieverId(userInfo1.getUserNum());
        messageInfo1.setSenderId(userInfo2.getUserNum());
        messageInfo1.setSendDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo1.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo1.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo1.setCurrentStatus(1);

        messageDao.insertMessageInfo(messageInfo1);
        MessageInfo messageInfo = messageDao.queryMessageInfoByMessageId(messageInfo1.getMessageId());
        Assert.assertNotNull(messageInfo);
        Assert.assertEquals(messageInfo,messageInfo1);

        messageInfo1.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageDao.updateMessageInfo(messageInfo1);
        messageInfo = messageDao.queryMessageInfoByMessageId(messageInfo1.getMessageId());
        Assert.assertEquals(messageInfo,messageInfo1);

        List<MessageInfo> messageInfos = new LinkedList<>();
        MessageInfo messageInfo2 = new MessageInfo();
        messageInfo2.setMessageId(UUID.randomUUID().toString());
        messageInfo2.setRecieverId(userInfo1.getUserNum());
        messageInfo2.setSenderId(userInfo2.getUserNum());
        messageInfo2.setSendDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo2.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo2.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo2.setCurrentStatus(1);
        messageInfos.add(messageInfo2);
        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setMessageId(UUID.randomUUID().toString());
        messageInfo3.setRecieverId(userInfo1.getUserNum());
        messageInfo3.setSenderId(userInfo2.getUserNum());
        messageInfo3.setSendDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo3.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo3.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageInfo3.setCurrentStatus(1);
        messageInfos.add(messageInfo3);

        messageDao.insertMessageInfos(messageInfos);
        List<MessageInfo> queryMessages = messageDao.queryMessageInfoAll();
        Assert.assertNotNull(queryMessages);
        Assert.assertEquals(queryMessages.size(),3);
        List<MessageInfo> queryMessagesForSenderId = messageDao.queryMessageInfosBySenderId(messageInfo1.getSenderId());
        Assert.assertNotNull(queryMessagesForSenderId);
        Assert.assertEquals(queryMessagesForSenderId.size(),3);
        List<MessageInfo> queryMessageForRecieverId = messageDao.queryMessageInfosByRecieverId(messageInfo1.getRecieverId());
        Assert.assertNotNull(queryMessageForRecieverId);
        Assert.assertEquals(queryMessageForRecieverId.size(),3);

        messageDao.deleteMessageInfoByMessageId(messageInfo1.getMessageId());
        MessageInfo queryMessage1 = messageDao.queryMessageInfoByMessageId(messageInfo1.getMessageId());
        Assert.assertNull(queryMessage1);

        List<String> messageIds = new LinkedList<>();
        for(MessageInfo messageInfoIter : queryMessages){
            messageIds.add(messageInfoIter.getMessageId());
        }
        messageDao.deleteMessageInfosByMessageIds(messageIds);
        List<MessageInfo> queryDelMessages = messageDao.queryMessageInfoAll();
        Assert.assertNotNull(queryDelMessages);
        Assert.assertEquals(queryDelMessages.size(),0);
    }
}
