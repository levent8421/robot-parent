package com.levent8421.robot.web.controller;

import com.levent8421.robot.hardware.controller.CarController;
import com.levent8421.robot.web.vo.AllDirTestParam;
import com.levent8421.robot.web.vo.GeneralResult;
import com.levent8421.robot.web.vo.SpeedAngleParam;
import com.levent8421.robot.web.vo.StartStopParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class BasicController implements ThreadFactory, Runnable {
    private static final int MAX_ANGLE = 360;
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(), this);
    private final CarController carController;
    private int stepDelay;
    private int speed;

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

    @PostMapping("/_all-dir-test")
    public GeneralResult<Void> runAllDirTest(@RequestBody AllDirTestParam param) {
        if (param == null || param.getSpeed() == null || param.getStepDelay() == null) {
            return GeneralResult.badRequest("参数错误");
        }
        this.stepDelay = param.getStepDelay();
        this.speed = param.getSpeed();
        executor.execute(this);
        return GeneralResult.ok();
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }

    @Override
    public void run() {
        for (int i = 0; i < MAX_ANGLE; i++) {
            carController.go(i, speed);
            try {
                Thread.sleep(stepDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
