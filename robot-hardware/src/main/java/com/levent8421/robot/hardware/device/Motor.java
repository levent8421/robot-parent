package com.levent8421.robot.hardware.device;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 9:40
 * Class Name: Motor
 * Author: Levent8421
 * Description:
 * 电机控制抽象
 *
 * @author Levent8421
 */
public interface Motor {
    /**
     * 方向：前进
     */
    int DIR_FORWARD = 0x00;
    /**
     * 方向：后退
     */
    int DIR_BACKWARD = 0x01;

    /**
     * Initialize this device
     */
    void setup();

    /**
     * 设置方向
     *
     * @param dir 方向
     */
    void setDirection(int dir);

    /**
     * 获取方向
     *
     * @return 方向
     */
    int getDirection();

    /**
     * 设置速度
     *
     * @param speed 速度 为负值时为倒转
     */
    void setSpeed(int speed);

    /**
     * 获取速度
     *
     * @return 速度
     */
    int getSpeed();

    /**
     * 停止
     */
    void stop();

    /**
     * 启动电机
     * 速度为正值时运行方向为向前，速度为负值时运行方向为向后
     *
     * @param speed 速度
     */
    void go(int speed);
}
