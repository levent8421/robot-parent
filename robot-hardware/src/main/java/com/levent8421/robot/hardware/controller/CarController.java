package com.levent8421.robot.hardware.controller;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 16:12
 * Class Name: CarController
 * Author: Levent8421
 * Description:
 * 小车相关控制器，控制小车行走
 *
 * @author Levent8421
 */
public interface CarController {
    int MOTOR_FRONT_LEFT = 1;
    int MOTOR_FRONT_RIGHT = 2;
    int MOTOR_BACK_LEFT = 3;
    int MOTOR_BACK_RIGHT = 4;

    /**
     * Initialize this controller
     */
    void setup();

    /**
     * Move with angle and speed
     *
     * @param angle angle
     * @param speed speed
     */
    void go(double angle, int speed);

    /**
     * Stop move
     */
    void stop();

    /**
     * start move
     */
    void start();

    /**
     * 设置指定电机的速度
     *
     * @param motor 电机编号
     * @param speed 速度 为负值时为倒转
     */
    void setMotorSpeed(int motor, int speed);
}
