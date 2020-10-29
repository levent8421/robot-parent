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
}
