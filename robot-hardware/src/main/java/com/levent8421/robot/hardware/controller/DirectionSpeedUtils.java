package com.levent8421.robot.hardware.controller;

/**
 * Create By leven ont 2020/11/3 23:54
 * Class Name :[DirectionSpeedUtils]
 * <p>
 * 方向控制工具类
 * <p>
 * --		进	退
 * 前左	右	左
 * 前右	左	右
 * 后左	左	右
 * 后右	右	左
 * ---	FL	FR	BL	BR
 * 0	 1	 1	 1	 1
 * 45	 1	 0	 0	 1
 * 90	 1	-1	-1	 1
 * 135	 0	-1	-1	 0
 * 180	-1	-1	-1	-1
 * 225	-1	 0	 0	-1
 * 270	-1	 1	 1	-1
 * 315	 0	 1	 1	 0
 *
 * @author leven
 */
public class DirectionSpeedUtils {
    private static final double[] SPEED_CURVE_VALUES = {1, 1, 1, 0, -1, -1, -1, 0};
    private static final double MAX_ANGLE = 360;
    private static final double VALUE_STEP = 360 / 8;

    private static final double FRONT_LEFT_ANGLE_OFFSET = 0;
    private static final double FRONT_RIGHT_ANGLE_OFFSET = 90;
    private static final double BACK_LEFT_ANGLE_OFFSET = 90;
    private static final double BACK_RIGHT_ANGLE_OFFSET = 0;

    /**
     * 计算要使小车向指定方向以指定速度移动每个马达所需的速度
     *
     * @param speed    速度
     * @param angle    角度
     * @param maxSpeed 最大速度
     * @param res      计算结果对象，原样返回，若为空将会闯将新对象
     * @return 计算结果包装对象
     */
    public static DirectionSpeed calcDirectionSpeed(int speed, double angle, int maxSpeed, DirectionSpeed res) {
        if (res == null) {
            res = new DirectionSpeed();
        }
        final double frontLeftMaxSpeed = calcSpeed(angle, FRONT_LEFT_ANGLE_OFFSET, maxSpeed);
        final double frontRightMaxSpeed = calcSpeed(angle, FRONT_RIGHT_ANGLE_OFFSET, maxSpeed);
        final double backLeftMaxSpeed = calcSpeed(angle, BACK_LEFT_ANGLE_OFFSET, maxSpeed);
        final double backRightMaxSpeed = calcSpeed(angle, BACK_RIGHT_ANGLE_OFFSET, maxSpeed);

        final double proportion = ((double) speed) / maxSpeed;

        res.setFrontLeft((int) (frontLeftMaxSpeed * proportion));
        res.setFrontRight((int) (frontRightMaxSpeed * proportion));
        res.setBackLeft((int) (backLeftMaxSpeed * proportion));
        res.setBackRight((int) (backRightMaxSpeed * proportion));
        return res;
    }

    private static double calcSpeed(double angle, double offset, int maxSpeed) {
        angle += offset;
        angle %= MAX_ANGLE;
        final int beginIndex = (int) (angle / VALUE_STEP);
        final int endIndex;
        if (beginIndex >= SPEED_CURVE_VALUES.length - 1) {
            endIndex = 0;
        } else {
            endIndex = beginIndex + 1;
        }
        final double beginSpeed = SPEED_CURVE_VALUES[beginIndex] * maxSpeed;
        final double endSpeed = SPEED_CURVE_VALUES[endIndex] * maxSpeed;
        final double stepSpeed = endSpeed - beginSpeed;
        final double angleOffset = angle - (beginIndex * VALUE_STEP);
        final double speedOffset = angleOffset * stepSpeed / VALUE_STEP;
        return beginSpeed + speedOffset;
    }
}
