package com.levent8421.robot.hardware.pid;

import org.junit.Test;

public class PIDLooperTest implements PIDResultListener {
    private PIDController controller;

    @Test
    public void test() throws InterruptedException {
        final PIDSetting setting = new PIDSetting();
        setting.setCycle(0.1);
        setting.setKp(0.5);
        setting.setKi(0.02);
        setting.setKd(0.01);
        setting.setDeltaMax(255);
        setting.setDeltaMin(-255);
        controller = new SimplePIDController(setting);
        final PIDControllerGroup controllerGroup = new PIDControllerGroup();
        controllerGroup.addController(1, controller);
        final PIDLooper looper = new PIDLooper(controllerGroup, 100, this);
        looper.start();
        Thread.sleep(1000);
        controller.updateTarget(1000);
        Thread.sleep(100 * 1000);
    }

    public void onResult(PIDControllerGroup controllerGroup) {
        final double outputDelta = controllerGroup.getOutputDelta(1);
        final double current = controller.getCurrent();
        final String msg = String.format("%f   --  %f", current, outputDelta);
        System.out.println(msg);
        controller.updateCurrent(current + outputDelta);
    }
}
