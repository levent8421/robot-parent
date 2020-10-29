package com.levent8421.robot.hardware.controller;

import com.levent8421.robot.hardware.device.DigitalOutputDevice;
import com.levent8421.robot.hardware.device.Motor;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 16:25
 * Class Name: SimpleCarController
 * Author: Levent8421
 * Description:
 * Car controller
 *
 * @author Levent8421
 */
public class SimpleCarController implements CarController {
    private final DigitalOutputDevice enOutput;
    private final Motor flMotor;
    private final Motor frMotor;
    private final Motor blMotor;
    private final Motor brMotor;
    private volatile int speed = 0;
    private volatile int flSpeed = 0;
    private volatile int frSpeed = 0;
    private volatile int blSpeed = 0;
    private volatile int brSpeed = 0;

    public SimpleCarController(DigitalOutputDevice enOutput,
                               Motor flMotor, Motor frMotor, Motor blMotor, Motor brMotor) {
        this.enOutput = enOutput;
        this.flMotor = flMotor;
        this.frMotor = frMotor;
        this.blMotor = blMotor;
        this.brMotor = brMotor;
    }

    @Override
    public void setup() {
        enOutput.setup();
        flMotor.setup();
        frMotor.setup();
        blMotor.setup();
        brMotor.setup();
    }

    @Override
    public void go(double angle, int speed) {

    }

    @Override
    public void stop() {
        enOutput.pollDown();
    }

    @Override
    public void start() {
        enOutput.pollUp();
    }

    @Override
    public void setMotorSpeed(int motor, int speed) {
        switch (motor) {
            case MOTOR_FRONT_LEFT:
                flMotor.setSpeed(speed);
                break;
            case MOTOR_FRONT_RIGHT:
                frMotor.setSpeed(speed);
                break;
            case MOTOR_BACK_LEFT:
                blMotor.setSpeed(speed);
                break;
            case MOTOR_BACK_RIGHT:
                brMotor.setSpeed(speed);
                break;
            default:
                throw new IllegalArgumentException("Unknown motor " + motor);
        }
    }
}
