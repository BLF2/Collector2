package net.blf2.exception;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by blf2 on 17-4-13.
 */
public class UpdateException extends RuntimeException {
    private static Log logger = LogFactory.getLog(new UpdateException().getClass());
    public UpdateException() {
    }

    public UpdateException(Object o) {
        super(o.getClass().toString() + "更新信息出错！");
        logger.error(super.getMessage());
    }
}
