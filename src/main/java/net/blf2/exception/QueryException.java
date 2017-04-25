package net.blf2.exception;

/**
 * Created by blf2 on 17-4-13.
 */
public class QueryException extends RuntimeException {
    public QueryException() {
    }

    public QueryException(Object o) {
        super(o.getClass().toString() + "查询信息出错！");
    }
}
