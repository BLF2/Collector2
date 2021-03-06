package net.blf2.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by blf2 on 17-4-13.
 */
public class QueryException extends RuntimeException {
    private static Log logger = LogFactory.getLog(new QueryException().getClass());
    public QueryException() {
    }

    public QueryException(Object o) {
        super(o.getClass().toString() + "查询信息出错！");
        logger.error(super.getMessage());
    }
}
