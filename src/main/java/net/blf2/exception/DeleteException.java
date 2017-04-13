package net.blf2.exception;

/**
 * Created by blf2 on 17-4-13.
 */
public class DeleteException extends RuntimeException {
    public DeleteException() {
    }

    public DeleteException(Object o) {
        super(o.getClass().toString() + "删除数据出错!");
    }
}
