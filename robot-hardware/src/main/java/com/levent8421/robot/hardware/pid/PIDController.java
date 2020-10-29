package com.levent8421.robot.hardware.pid;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 11:28
 * Class Name: PIDController
 * Author: Levent8421
 * Description:
 * PID控制器
 *
 * @author Levent8421
 */
public interface PIDController {
    /**
     * 指定目标值
     *
     * @param target 目标值
     */
    void updateTarget(double target);

    /**
     * 获取目标值
     *
     * @return 目标值
     */
    double getTarget();

    /**
     * 更新测量值
     *
     * @param current 测量值
     */
    void updateCurrent(double current);

    /**
     * 获取当前值
     *
     * @return 当前值
     */
    double getCurrent();

    /**
     * 重置
     */
    void reset();

    /**
     * 获取输出值
     *
     * @return delta
     */
    double calcDelta();
}
