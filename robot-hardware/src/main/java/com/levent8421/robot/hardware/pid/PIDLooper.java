package com.levent8421.robot.hardware.pid;

import java.util.concurrent.*;

/**
 * Create By Levent8421
 * Create Time: 2020/10/23 15:12
 * Class Name: PIDLooper
 * Author: Levent8421
 * Description:
 * PIDLooper
 *
 * @author Levent8421
 */
public class PIDLooper implements ThreadFactory, Runnable {
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>();
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0,
            TimeUnit.MILLISECONDS, taskQueue, this);
    private final PIDControllerGroup controllerGroup;
    private boolean running = false;
    private final int interval;
    private final PIDResultListener pidResultListener;

    public PIDLooper(PIDControllerGroup controllerGroup, int interval, PIDResultListener pidResultListener) {
        this.controllerGroup = controllerGroup;
        this.interval = interval;
        this.pidResultListener = pidResultListener;
    }

    public synchronized void start() {
        if (running || taskQueue.size() > 0) {
            throw new IllegalStateException("Looper are early started!");
        }
        running = true;
        executor.execute(this);
    }

    public synchronized void stop() {
        running = false;
        executor.shutdownNow();
    }

    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(r);
        thread.setName("PIDLooper");
        return thread;
    }

    @Override
    public void run() {
        while (running) {
            controllerGroup.calcOut();
            this.pidResultListener.onResult(controllerGroup);
            trySleep(this.interval);
        }
    }

    private void trySleep(int interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
