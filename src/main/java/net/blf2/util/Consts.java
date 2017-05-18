package net.blf2.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by blf2 on 17-4-5.
 */
public class Consts {
    private Consts(){
        super();
    }
    private static Properties props;
    static{
        Resource resource = new ClassPathResource("/application.properties");
        try {
           props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final int CONNECTIONS_PER_HOST = props == null ? 50 : Integer.parseInt(props.getProperty("CONNECTIONS_PER_HOST", "50"));
    public static final int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = props == null ? 50 : Integer.parseInt(props.getProperty("THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER","50"));
    public static final int CONNECT_TIMEOUT = props == null ? 60000 : Integer.parseInt(props.getProperty("CONNECT_TIMEOUT","60000"));
    public static final int MAX_WAIT_TIME = props == null ? 120000 : Integer.parseInt(props.getProperty("MAX_WAIT_TIME", "120000"));
    public static final String MONGODB_HOST = props == null ? "127.0.0.1" : props.getProperty("MONGODB_HOST","127.0.0.1").trim();
    public static final int MONGODB_PORT = props == null ? 27017 : Integer.parseInt(props.getProperty("MONGODB_PORT", "27017"));
    public static final int SOCKET_TIME_OUT = props == null ? 5000 : Integer.parseInt(props.getProperty("SOCKET_TIME_OUT","5000"));
    public static final boolean SOCKET_KEEP_ALIVE = props == null ? false : Boolean.parseBoolean(props.getProperty("SOCKET_KEEP_ALIVE","false"));
    public static final String MONGO_DATABASE_NAME = "app";
    public static final String COLLECTION_FOR_FORM = "FormInfo";
    public static final String DIY_FORM_INFO = "DIYFormInfo";//自定义表单名称，用于存入mongo的key
    public static final String FORM_RESULT_COLLECTION_NAME = "FormResult";//表单收集的数据
    public static final String ERROR_MESSAGE_FOR_PAGE = "ErrorMsgForPage";
    public static final String CURRENT_USER = "CurrentUser";
    public static final String ADMIN_ROLE_NAME = "admin";
    public static final String MONITOR_ROLE_NAME = "monitor";
    public static final String PRIMARY_ROLE_NAME = "primary";
    public static final String ADMIN_ROLE_ID = "";
    public static final String MONITOR_ROLE_ID = "";
    public static final String PRIMARY_ROLE_ID = "";
    public static final String DATABASE_ERROR = "操作失败，数据库出错，如果多吃出现，请联系管理员。";
    public static final String OPRERATOR_MESSAGE = "operatorMessage";
}
