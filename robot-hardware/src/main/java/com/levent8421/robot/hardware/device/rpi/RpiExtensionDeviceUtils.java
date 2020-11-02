package com.levent8421.robot.hardware.device.rpi;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.i2c.I2CBus;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Create By leven ont 2020/11/2 21:58
 * Class Name :[RpiExtensionDeviceUtils]
 * <p>
 * RaspberryPi Extension Modal Utils
 *
 * @author leven
 */
public class RpiExtensionDeviceUtils {
    /**
     * 初始化PCA9685模块的PWM输出功能
     *
     * @param provider       PCA9685GpioProvider
     * @param gpioController GpioController
     * @return pwm pins
     */
    public static GpioPinPwmOutput[] initPca9685Pwm(PCA9685GpioProvider provider, GpioController gpioController) {
        return new GpioPinPwmOutput[]{
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_00, "PWM_00"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_01, "PWM_01"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_02, "PWM_02"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_03, "PWM_03"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_04, "PWM_04"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_05, "PWM_05"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_06, "PWM_06"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_07, "PWM_07"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_08, "PWM_08"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_09, "PWM_09"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_10, "PWM_10"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_11, "PWM_11"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_12, "PWM_12"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_13, "PWM_13"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_14, "PWM_14"),
                gpioController.provisionPwmOutputPin(provider, PCA9685Pin.PWM_15, "PWM_15")};
    }

    /**
     * 构建PCA9685模块输出
     *
     * @param bus        I2C BUS
     * @param i2cAddress I2C ADDRESS
     * @param frequency  freq
     * @return provider
     * @throws IOException IOE
     */
    public static PCA9685GpioProvider buildPca9685GpioProvider(I2CBus bus, int i2cAddress, BigDecimal frequency) throws IOException {
        return new PCA9685GpioProvider(bus, i2cAddress, frequency);
    }
}
