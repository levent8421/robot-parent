package com.levent8421.robot.hardware.pid;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 15:16
 * Class Name: PIDResultListener
 * Author: Levent8421
 * Description:
 * PID Result Delta Listener
 *
 * @author Levent8421
 */
public interface PIDResultListener {
    /**
     * PID运算结果回调
     *
     * @param controllerGroup controller group
     */
    void onResult(PIDControllerGroup controllerGroup);
}
