package com.levent8421.robot.hardware.pid;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 11:33
 * Class Name: PIDSetting
 * Author: Levent8421
 * Description:
 * PID设置参数
 *
 * @author Levent8421
 */

public class PIDSetting {
    /**
     * 偏差比例系数
     */
    private double kp;
    /**
     * 偏差积分系数
     */
    private double ki;
    /**
     * 偏差微分系数
     */
    private double kd;
    /**
     * 最大输出
     */
    private double deltaMax;
    /**
     * 最小输出
     */
    private double deltaMin;
    /**
     * 采样周期
     */
    private double cycle;

    public double getKp() {
        return kp;
    }

    public void setKp(double kp) {
        this.kp = kp;
    }

    public double getKi() {
        return ki;
    }

    public void setKi(double ki) {
        this.ki = ki;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public double getDeltaMax() {
        return deltaMax;
    }

    public void setDeltaMax(double deltaMax) {
        this.deltaMax = deltaMax;
    }

    public double getDeltaMin() {
        return deltaMin;
    }

    public void setDeltaMin(double deltaMin) {
        this.deltaMin = deltaMin;
    }

    public double getCycle() {
        return cycle;
    }

    public void setCycle(double cycle) {
        this.cycle = cycle;
    }
}
