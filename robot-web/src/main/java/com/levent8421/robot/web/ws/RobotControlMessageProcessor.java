package com.levent8421.robot.web.ws;

import com.levent8421.robot.hardware.controller.CarController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Create By leven ont 2020/10/29 22:00
 * Class Name :[RobotControlMessageProcessor]
 * <p>
 * 机器人控制消息处理
 *
 * @author leven
 */
@Component
@Slf4j
public class RobotControlMessageProcessor {
    private final CarController carController;

    public RobotControlMessageProcessor(CarController carController) {
        this.carController = carController;
    }

    void process(RobotControlMessage message) {
        switch (message.getAction()) {
            case "set_speed":
                this.setSpeed(message.getMotor(),
                        message.getSpeed());
                break;
            case "enable":
                this.setState(message.getState());
                break;
            default:
                // Do Nothing
        }
    }

    private void setSpeed(Integer motor, Integer speed) {
        if (motor == null || speed == null) {
            log.warn("Motor of speed is null!");
            return;
        }
        log.debug("Set motor[{}] speed to [{}]", motor, speed);
        carController.setMotorSpeed(motor, speed)   ;
    }

    private void setState(Boolean state) {
        if (state == null) {
            log.warn("State id null");
            return;
        }
        log.debug("Set state to [{}]", state);
        if (state) {
            carController.start();
        } else {
            carController.stop();
        }
    }
}
