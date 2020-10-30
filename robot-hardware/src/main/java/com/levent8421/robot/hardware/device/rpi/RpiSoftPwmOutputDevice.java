package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.PwmOutputDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;

/**
 * Create By leven ont 2020/10/30 23:37
 * Class Name :[RpiSoftPwmOutputDevice]
 * <p>
 * RaspberryPi soft PWM Output device
 *
 * @author leven
 */
public class RpiSoftPwmOutputDevice extends AbstractRpiPwmOutputDevice implements PwmOutputDevice {

    public RpiSoftPwmOutputDevice(GpioController gpioController, Pin pin, int pwmRange) {
        super(gpioController, pin, pwmRange);
    }

    @Override
    protected GpioPinPwmOutput getPwmOutputPin(GpioController gpioController, Pin pin) {
        return gpioController.provisionSoftPwmOutputPin(pin);
    }
}
