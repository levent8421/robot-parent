package com.levent8421.robot.hardware.controller;

import lombok.Data;

/**
 * Create By leven ont 2020/11/3 23:39
 * Class Name :[DirectionSpeed]
 * <p>
 * 四个马达的速度数据对象
 *
 * @author leven
 */
@Data
public class DirectionSpeed {
    private static final DirectionSpeed INSTANCE = new DirectionSpeed();

    public static DirectionSpeed getInstance() {
        return INSTANCE.reset();
    }

    private DirectionSpeed reset() {
        frontLeft = 0;
        frontRight = 0;
        backLeft = 0;
        backRight = 0;
        return this;
    }

    /**
     * 左前速度
     */
    private int frontLeft;
    /**
     * 右前速度
     */
    private int frontRight;
    /**
     * 左后速度
     */
    private int backLeft;
    /**
     * 右后速度
     */
    private int backRight;

    @Override
    public String toString() {
        return String.format("DirectionSpeed[FL=%d,FR=%d,BL=%d,BR=%d]", frontLeft, frontRight, backLeft, backRight);
    }
}
