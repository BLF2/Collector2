package net.blf2.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by blf2 on 17-4-13.
 */
public class InsertException extends RuntimeException {
    private static Log logger = LogFactory.getLog(new InsertException().getClass());
    public InsertException() {
    }

    public InsertException(Object o) {
        super(o.getClass().toString() + "插入数据出错！");
        logger.error(super.getMessage());
    }
}
