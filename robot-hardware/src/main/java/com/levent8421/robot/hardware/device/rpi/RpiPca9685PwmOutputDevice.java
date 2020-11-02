package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.PwmOutputDevice;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.io.gpio.Pin;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class RpiPca9685PwmOutputDevice implements PwmOutputDevice {
    private static final int MIN_DURATION = 1;
    private final PCA9685GpioProvider provider;
    private final Pin pin;
    private volatile int dutyCycle;

    public RpiPca9685PwmOutputDevice(PCA9685GpioProvider provider, Pin pin) {
        this.provider = provider;
        this.pin = pin;
    }

    @Override
    public void setup() {
        off();
    }

    @Override
    public void off() {
        provider.setAlwaysOff(pin);
        dutyCycle = MIN_DURATION;
    }

    @Override
    public void on() {
        provider.setAlwaysOn(pin);
        dutyCycle = provider.getPeriodDurationMicros();
    }

    @Override
    public void setDutyCycle(int dutyCycle) {
        if (dutyCycle < MIN_DURATION) {
            log.warn("Can not set duration [{}] for PCA_PWM Device, duration reset to [{}]!", dutyCycle, MIN_DURATION);
            dutyCycle = MIN_DURATION;
        }
        if (dutyCycle >= provider.getPeriodDurationMicros()) {
            log.warn("Can not set duration [{}] for PCA_PWM Device, duration reset to [{}]!", dutyCycle, provider.getPeriodDurationMicros());
            dutyCycle = provider.getPeriodDurationMicros() - 1;
        }
        provider.setPwm(pin, dutyCycle);
        this.dutyCycle = dutyCycle;
    }

    @Override
    public int getDutyCycle() {
        return dutyCycle;
    }
}
