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
public class RpiPwmOutputDevice extends AbstractRpiPwmOutputDevice implements PwmOutputDevice {
    public RpiPwmOutputDevice(GpioController gpioController, Pin pin, int pwmRange) {
        super(gpioController, pin, pwmRange);
    }

    @Override
    protected GpioPinPwmOutput getPwmOutputPin(GpioController gpioController, Pin pin) {
        return gpioController.provisionPwmOutputPin(pin);
    }
}
