package com.levent8421.robot.web.robot;

import com.levent8421.robot.hardware.controller.CarController;
import com.levent8421.robot.hardware.controller.SimpleCarController;
import com.levent8421.robot.hardware.device.DigitalOutputDevice;
import com.levent8421.robot.hardware.device.Motor;
import com.levent8421.robot.hardware.device.rpi.*;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Create By leven ont 2020/10/29 22:15
 * Class Name :[RobotConfiguration]
 * <p>
 * 配置机器人控制器
 *
 * @author leven
 */
@ConfigurationProperties("pwm")
@Configuration
public class RobotConfiguration {
    private static final int PWM_RANGE = 1024;
    private static final int PCA_9685_I2C_BUS = I2CBus.BUS_1;
    private static final int PCA_9685_I2C_ADDRESS = 0x40;
    private static final BigDecimal PCA_9685_ICA_FREQUENCY = PCA9685GpioProvider.MAX_FREQUENCY;

    private static Motor buildPcaMotor(GpioController gpioController, PCA9685GpioProvider provider,
                                       Pin dirPin, Pin pwmPin) {
        final RpiPca9685PwmOutputDevice pwmOutput = new RpiPca9685PwmOutputDevice(provider, pwmPin);
        final DigitalOutputDevice dirOutput = new RpiDigitalOutputDevice(gpioController, dirPin);
        return new RpiPwmMotor(dirOutput, pwmOutput);
    }

    private static PCA9685GpioProvider initPca9685(GpioController gpioController) {
        final I2CBus bus;
        try {
            bus = I2CFactory.getInstance(PCA_9685_I2C_BUS);
        } catch (Exception e) {
            throw new RobotInitException("Error in Init I2C BUS!", e);
        }
        try {
            final PCA9685GpioProvider provider = RpiExtensionDeviceUtils.buildPca9685GpioProvider(bus, PCA_9685_I2C_ADDRESS, PCA_9685_ICA_FREQUENCY);
            RpiExtensionDeviceUtils.initPca9685Pwm(provider, gpioController);
            return provider;
        } catch (IOException e) {
            throw new RobotInitException("Error on init PCA9685 GPIO Provider!");
        }
    }

    @Setter
    private Integer frequencyDivision = 2;

    @Bean
    public CarController carController() {
        final GpioController gpioController = RpiDeviceUtils.getGpioController();
        final PCA9685GpioProvider provider = initPca9685(gpioController);

        final Motor blMotor = buildPcaMotor(gpioController, provider, RaspiPin.GPIO_25, PCA9685Pin.PWM_00);
        final Motor flMotor = buildPcaMotor(gpioController, provider, RaspiPin.GPIO_29, PCA9685Pin.PWM_01);
        final Motor brMotor = buildPcaMotor(gpioController, provider, RaspiPin.GPIO_28, PCA9685Pin.PWM_02);
        final Motor frMotor = buildPcaMotor(gpioController, provider, RaspiPin.GPIO_27, PCA9685Pin.PWM_03);

        final DigitalOutputDevice enPin = new RpiDigitalOutputDevice(gpioController, RaspiPin.GPIO_07);
        final CarController controller = new SimpleCarController(enPin, flMotor, frMotor, blMotor, brMotor);
        controller.setup();
        RpiDeviceUtils.setupPwm(PWM_RANGE, frequencyDivision);
        return controller;
    }
}
