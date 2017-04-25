package net.blf2.exception;

/**
 * Created by blf2 on 17-4-13.
 */
public class InsertException extends RuntimeException {
    public InsertException() {
    }

    public InsertException(Object o) {
        super(o.getClass().toString() + "插入数据出错！");
    }
}
