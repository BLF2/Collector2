package net.blf2.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by blf2 on 17-4-13.
 */
public class DeleteException extends RuntimeException {
    private static Log logger = LogFactory.getLog(new DeleteException().getClass());
    public DeleteException() {
    }

    public DeleteException(Object o) {
        super(o.getClass().toString() + "删除数据出错!");
        logger.error(super.getMessage());
    }
}
