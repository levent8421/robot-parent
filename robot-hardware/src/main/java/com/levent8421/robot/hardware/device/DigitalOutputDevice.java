package com.levent8421.robot.hardware.device;

/**
 * Create By Levent8421
 * Create Time: 2020/10/28 11:25
 * Class Name: DigitalOutputDevice
 * Author: Levent8421
 * Description:
 * 数字输出设备
 *
 * @author Levent8421
 */
public interface DigitalOutputDevice {
    int STATE_HIGH = 1;
    int STATE_LOW = 0;

    /**
     * Initialize this pin
     */
    void setup();

    /**
     * Shutdown this pin
     */
    void shutdown();

    /**
     * Poll up for this pin
     */
    void pollUp();

    /**
     * Poll down for this pin
     */
    void pollDown();

    /**
     * toggle state for this pin
     */
    void toggle();

    /**
     * get output state
     *
     * @return state code
     */
    int getState();
}
