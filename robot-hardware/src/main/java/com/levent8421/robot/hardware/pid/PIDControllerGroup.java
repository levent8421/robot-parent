package com.levent8421.robot.hardware.pid;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 15:01
 * Class Name: PIDControllerGroup
 * Author: Levent8421
 * Description:
 * PID控制器组
 *
 * @author Levent8421
 */
public class PIDControllerGroup {
    private final Map<Integer, PIDController> controllerMap = new HashMap<>(16);
    private final Map<Integer, Double> outputDeltaMap = new HashMap<>(16);

    public synchronized void calcOut() {
        for (Map.Entry<Integer, PIDController> entry : controllerMap.entrySet()) {
            final PIDController controller = entry.getValue();
            final Integer key = entry.getKey();
            final double delta = controller.calcDelta();
            outputDeltaMap.put(key, delta);
        }
    }

    public double getOutputDelta(int key) {
        if (!outputDeltaMap.containsKey(key)) {
            throw new IllegalStateException("Result for controller[" + key + "] not ready!");
        }
        return outputDeltaMap.get(key);
    }

    public Map<Integer, Double> getAllOutputDelta() {
        return new HashMap<>(this.outputDeltaMap);
    }

    public PIDController removeController(int key) {
        return controllerMap.remove(key);
    }

    public PIDControllerGroup addController(int key, PIDController controller) {
        this.controllerMap.put(key, controller);
        return this;
    }
}
