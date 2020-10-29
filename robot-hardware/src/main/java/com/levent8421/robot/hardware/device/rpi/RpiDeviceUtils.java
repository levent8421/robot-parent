package com.levent8421.robot.hardware.device.rpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import lombok.extern.slf4j.Slf4j;

/**
 * Create By Levent8421
 * Create Time: 2020/10/28 11:25
 * Class Name: RpiDeviceUtils
 * Author: Levent8421
 * Description:
 * RaspberryPi 设备相关工具类
 *
 * @author Levent8421
 */
@Slf4j
public class RpiDeviceUtils {
    /**
     * PWM时钟频率， 树莓派PWM时钟频率为19.2M
     */
    private static final int PWM_SOURCE_FREQ = 19200 * 1000;
    private static final GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    /**
     * Get GPIO Controller
     *
     * @return GPIO Controller
     */
    public static GpioController getGpioController() {
        return GPIO_CONTROLLER;
    }

    /**
     * Initialize board PWM
     *
     * @param range duty cycle range
     * @param clock pwm clock
     */
    public static void setupPwm(int range, int clock) {
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetRange(range);
        Gpio.pwmSetClock(clock);
        log.debug("Set pwm params: [range/clock]: [{}/{}]", range, clock);
    }

    /**
     * Reset GPIO
     */
    public static void cleanup() {
        GPIO_CONTROLLER.shutdown();
    }

    /**
     * 计算PWM时钟分频频率
     *
     * @param pwmFreq  目标频率
     * @param pwmRange 目标频率
     * @return 分频频率
     */
    public static int calcPwmClock(int pwmFreq, int pwmRange) {
        return PWM_SOURCE_FREQ / pwmFreq / pwmRange;
    }
}
