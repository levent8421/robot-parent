package com.levent8421.robot.hardware.device;

/**
 * Create By Levent8421
 * Create Time: 2020/10/28 11:25
 * Class Name: PwmOutputDevice
 * Author: Levent8421
 * Description:
 * PWM 输出设备
 *
 * @author Levent8421
 */
public interface PwmOutputDevice {
    /**
     * Initialize this pwm pin
     */
    void setup();

    /**
     * set duty cycle to 0
     */
    void off();

    /**
     * set duty cycle to max value
     */
    void on();

    /**
     * set duty cycle
     *
     * @param dutyCycle duty cycle
     */
    void setDutyCycle(int dutyCycle);

    /**
     * Get duty cycle
     *
     * @return duty cycle
     */
    int getDutyCycle();
}
