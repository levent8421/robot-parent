package com.levent8421.robot.web.controller;

import com.levent8421.robot.hardware.controller.CarController;
import com.levent8421.robot.web.vo.GeneralResult;
import com.levent8421.robot.web.vo.SpeedAngleParam;
import com.levent8421.robot.web.vo.StartStopParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/11/4 17:04
 * Class Name: BasicController
 * Author: Levent8421
 * Description:
 * 基础控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/basic")
public class BasicController {
    private final CarController carController;

    public BasicController(CarController carController) {
        this.carController = carController;
    }

    @PostMapping("/_speed-and-angle")
    public GeneralResult<Void> setSpeedAndAngle(@RequestBody SpeedAngleParam param) {
        if (param == null || param.getAngle() == null || param.getSpeed() == null) {
            return GeneralResult.badRequest("参数错误");
        }
        carController.go(param.getAngle(), param.getSpeed());
        return GeneralResult.ok();
    }

    @PostMapping("/_start-stop")
    public GeneralResult<Void> startOrStop(@RequestBody StartStopParam param) {
        if (param == null || param.getState() == null) {
            return GeneralResult.badRequest("参数错误");
        }
        if (param.getState()) {
            carController.start();
        } else {
            carController.stop();
        }
        return GeneralResult.ok();
    }
}
