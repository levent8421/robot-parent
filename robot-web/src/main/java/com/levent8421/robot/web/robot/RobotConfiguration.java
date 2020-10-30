package com.levent8421.robot.web.robot;

import com.levent8421.robot.hardware.controller.CarController;
import com.levent8421.robot.hardware.controller.SimpleCarController;
import com.levent8421.robot.hardware.device.DigitalOutputDevice;
import com.levent8421.robot.hardware.device.Motor;
import com.levent8421.robot.hardware.device.rpi.RpiDeviceUtils;
import com.levent8421.robot.hardware.device.rpi.RpiDigitalOutputDevice;
import com.levent8421.robot.hardware.device.rpi.RpiPwmMotor;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.RaspiPin;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Setter
    private Integer frequencyDivision = 2;

    @Bean
    public CarController carController() {
        final GpioController gpioController = RpiDeviceUtils.getGpioController();
        final Motor flMotor = new RpiPwmMotor(gpioController, RaspiPin.GPIO_25, RaspiPin.GPIO_24, PWM_RANGE);
        final Motor frMotor = new RpiPwmMotor(gpioController, RaspiPin.GPIO_22, RaspiPin.GPIO_23, PWM_RANGE);
        final Motor blMotor = new RpiPwmMotor(gpioController, RaspiPin.GPIO_29, RaspiPin.GPIO_01, PWM_RANGE);
        final Motor brMotor = new RpiPwmMotor(gpioController, RaspiPin.GPIO_28, RaspiPin.GPIO_26, PWM_RANGE);
        final DigitalOutputDevice enPin = new RpiDigitalOutputDevice(gpioController, RaspiPin.GPIO_21);
        final CarController controller = new SimpleCarController(enPin, flMotor, frMotor, blMotor, brMotor);
        controller.setup();
        RpiDeviceUtils.setupPwm(PWM_RANGE, frequencyDivision);
        return controller;
    }
}
