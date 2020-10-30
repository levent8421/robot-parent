package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.PwmOutputDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import lombok.extern.slf4j.Slf4j;

/**
 * Create By leven ont 2020/10/30 23:40
 * Class Name :[RpiAbstractPwmOutputDevice]
 * <p>
 * RaspberryPi Pwm Output device
 *
 * @author leven
 */
@Slf4j
public abstract class AbstractRpiPwmOutputDevice implements PwmOutputDevice {
    private final GpioController gpioController;
    private final Pin pin;
    private final int pwmRange;
    private GpioPinPwmOutput pwm;
    private volatile int dutyCycle;

    public AbstractRpiPwmOutputDevice(GpioController gpioController, Pin pin, int pwmRange) {
        this.gpioController = gpioController;
        this.pin = pin;
        this.pwmRange = pwmRange;
    }

    @Override
    public void setup() {
        pwm = getPwmOutputPin(gpioController, pin);
        pwm.setPwmRange(pwmRange);
    }

    /**
     * 获取PWM输出设备
     *
     * @param gpioController GPIO controller
     * @param pin            pin
     * @return output pin
     */
    protected abstract GpioPinPwmOutput getPwmOutputPin(GpioController gpioController, Pin pin);

    @Override
    public void off() {
        setDutyCycle(0);
    }

    @Override
    public void on() {
        setDutyCycle(pwmRange);
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
