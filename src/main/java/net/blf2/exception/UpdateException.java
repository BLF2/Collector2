package net.blf2.exception;

/**
 * Created by blf2 on 17-4-13.
 */
public class UpdateException extends RuntimeException {
    public UpdateException() {
    }

    public UpdateException(Object o) {
        super(o.getClass().toString() + "更新信息出错！");
    }
}
