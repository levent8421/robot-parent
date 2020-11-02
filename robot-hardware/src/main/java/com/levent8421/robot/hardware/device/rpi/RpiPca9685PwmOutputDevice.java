package com.levent8421.robot.hardware.device.rpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;

/**
 * Create By Levent8421
 * Create Time: 2020/10/31 17:07
 * Class Name: RpiPca9685PwmOutputDevice
 * Author: Levent8421
 * Description:
 * RaspberryPi PCA9685 扩展模块pwm输出设备
 *
 * @author Levent8421
 */
public class RpiPca9685PwmOutputDevice extends AbstractRpiPwmOutputDevice {
    public RpiPca9685PwmOutputDevice(GpioController gpioController, Pin pin, int pwmRange) {
        super(gpioController, pin, pwmRange);
    }

    @Override
    protected GpioPinPwmOutput getPwmOutputPin(GpioController gpioController, Pin pin) {
        return null;
    }
}
