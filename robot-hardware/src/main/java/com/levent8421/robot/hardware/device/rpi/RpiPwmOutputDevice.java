package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.PwmOutputDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import lombok.extern.slf4j.Slf4j;

/**
 * Create By Levent8421
 * Create Time: 2020/10/28 12:24
 * Class Name: RpiPwmOutputDevice
 * Author: Levent8421
 * Description:
 * RaspberryPi PWM output Device
 *
 * @author Levent8421
 */
@Slf4j
public class RpiPwmOutputDevice implements PwmOutputDevice {
    private final GpioController gpioController;
    private final Pin pin;
    private final int maxDutyCycle;
    private volatile int dutyCycle = 0;
    private GpioPinPwmOutput pwm;

    public RpiPwmOutputDevice(GpioController gpioController, Pin pin, int maxDutyCycle) {
        this.gpioController = gpioController;
        this.pin = pin;
        this.maxDutyCycle = maxDutyCycle;
    }

    @Override
    public void setup() {
        pwm = gpioController.provisionPwmOutputPin(pin);
        pwm.setPwmRange(maxDutyCycle);
    }

    @Override
    public void off() {
        setDutyCycle(0);
    }

    @Override
    public void on() {
        setDutyCycle(maxDutyCycle);
    }

    @Override
    public void setDutyCycle(int dutyCycle) {
        pwm.setPwm(dutyCycle);
        this.dutyCycle = dutyCycle;
        log.debug("Set pin[{}] pwm to [{}]", pin.getName(), dutyCycle);
    }

    @Override
    public int getDutyCycle() {
        return dutyCycle;
    }
}
