package com.levent8421.robot.hardware.pid;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 13:34
 * Class Name: SimplePIDController
 * Author: Levent8421
 * Description:
 * PID算法实现
 *
 * @author Levent8421
 */
public class SimplePIDController implements PIDController {
    private double current = 0;
    private double target = 0;
    private double errorSum = 0;
    private double lastError = 0;
    private PIDSetting pidSetting;

    public SimplePIDController(PIDSetting setting) {
        if (setting == null) {
            throw new NullPointerException("Can not create Controller with a null setting!");
        }
        this.pidSetting = setting;
    }

    @Override
    public void updateTarget(double target) {
        this.target = target;
    }

    @Override
    public double getTarget() {
        return target;
    }

    @Override
    public void updateCurrent(double current) {
        this.current = current;
    }

    @Override
    public double getCurrent() {
        return current;
    }

    @Override
    public void reset() {
        this.lastError = 0;
        this.errorSum = 0;
    }

    @Override
    public double calcDelta() {
        final double currentError = target - current;
        this.errorSum += currentError;

        final double derivative = (currentError - this.lastError) * this.pidSetting.getCycle();
        this.lastError = currentError;

        double outDelta = this.pidSetting.getKp() * currentError
                + this.pidSetting.getKi() * this.errorSum
                + this.pidSetting.getKd() * derivative;

        outDelta = Math.max(pidSetting.getDeltaMin(), outDelta);
        outDelta = Math.min(pidSetting.getDeltaMax(), outDelta);
        return outDelta;
    }
}
