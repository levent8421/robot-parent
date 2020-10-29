package com.levent8421.robot.hardware.device.rpi;

import com.levent8421.robot.hardware.device.DigitalOutputDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

/**
 * Create By Levent8421
 * Create Time: 2020/10/28 11:26
 * Class Name: RpiDigitalOutputDevice
 * Author: Levent8421
 * Description:
 * RaspberryPi Digital Output Device
 *
 * @author Levent8421
 */
public class RpiDigitalOutputDevice implements DigitalOutputDevice {
    private final GpioController gpioController;
    private final Pin pin;
    private GpioPinDigitalOutput gpioPin;
    private volatile int state;

    public RpiDigitalOutputDevice(GpioController gpioController, Pin pin) {
        this.gpioController = gpioController;
        this.pin = pin;
    }

    @Override
    public void setup() {
        this.gpioPin = gpioController.provisionDigitalOutputPin(pin);
        this.pollDown();
        this.state = STATE_LOW;
    }

    @Override
    public void shutdown() {
        // Do nothing
    }

    @Override
    public void pollUp() {
        this.gpioPin.high();
        this.state = STATE_HIGH;
    }

    @Override
    public void pollDown() {
        this.gpioPin.low();
        this.state = STATE_LOW;
    }

    @Override
    public void toggle() {
        this.gpioPin.toggle();
        if (this.state == STATE_HIGH) {
            this.state = STATE_LOW;
        } else {
            this.state = STATE_HIGH;
        }
    }

    @Override
    public int getState() {
        return state;
    }
}
