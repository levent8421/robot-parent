package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.DigitalOutputDevice;
import com.levent8421.robot.hardware.device.Motor;
import com.levent8421.robot.hardware.device.PwmOutputDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import lombok.extern.slf4j.Slf4j;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 10:27
 * Class Name: RpiPwmMotor
 * Author: Levent8421
 * Description:
 * RaspberryPi PWM Motor
 *
 * @author Levent8421
 */
@Slf4j
public class RpiPwmMotor implements Motor {
    private final PwmOutputDevice pwmPin;
    private final DigitalOutputDevice dirPin;
    private volatile int dir;
    private volatile int speed;

    public RpiPwmMotor(GpioController gpioController, Pin dirPin, Pin pwmPin, int pwmRange) {
        this(buildDirDevice(gpioController, dirPin), buildPwmDevice(gpioController, pwmPin, pwmRange));
    }

    public RpiPwmMotor(DigitalOutputDevice dirOutput, PwmOutputDevice pwmOutput) {
        this.dirPin = dirOutput;
        this.pwmPin = pwmOutput;
    }

    private static DigitalOutputDevice buildDirDevice(GpioController gpioController, Pin pin) {
        return new RpiDigitalOutputDevice(gpioController, pin);
    }

    private static PwmOutputDevice buildPwmDevice(GpioController gpioController, Pin pin, int pwmRange) {
        return new RpiPwmOutputDevice(gpioController, pin, pwmRange);
    }

    @Override
    public void setup() {
        pwmPin.setup();
        dirPin.setup();
        this.setDirection(DIR_FORWARD);
        this.stop();
    }

    @Override
    public void setDirection(int dir) {
        switch (dir) {
            case DIR_FORWARD:
                dirPin.pollUp();
                break;
            case DIR_BACKWARD:
                dirPin.pollDown();
                break;
            default:
                throw new IllegalArgumentException("Unsupported direction " + dir);
        }
        this.dir = dir;
    }

    @Override
    public int getDirection() {
        return dir;
    }

    @Override
    public void setSpeed(int speed) {
        if (speed > 0) {
            setDirection(DIR_FORWARD);
        } else if (speed < 0) {
            setDirection(DIR_BACKWARD);
        }
        pwmPin.setDutyCycle(Math.abs(speed));
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void stop() {
        pwmPin.off();
    }

    @Override
    public void go(int speed) {
        if (speed == 0) {
            stop();
        } else if (speed > 0) {
            setDirection(DIR_FORWARD);
            setSpeed(speed);
        } else {
            setDirection(DIR_BACKWARD);
            setSpeed(Math.abs(speed));
        }
    }
}
