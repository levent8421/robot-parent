package com.levent8421.robot.web.robot;

/**
 * Create By leven ont 2020/11/2 22:23
 * Class Name :[RobotInitException]
 * <p>
 * 机器人初始化异常
 *
 * @author leven
 */
public class RobotInitException extends RuntimeException {
    public RobotInitException() {
    }

    public RobotInitException(String message) {
        super(message);
    }

    public RobotInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RobotInitException(Throwable cause) {
        super(cause);
    }

    public RobotInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
