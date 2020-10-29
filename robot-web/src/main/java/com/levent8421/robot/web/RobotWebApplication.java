package com.levent8421.robot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Create By Levent8421
 * Create Time: 2020/10/29 18:24
 * Class Name: RobotWebApplication
 * Author: Levent8421
 * Description:
 * System entry
 *
 * @author Levent8421
 */
@EnableWebSocket
@SpringBootApplication
public class RobotWebApplication {
    /**
     * Main method
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        SpringApplication.run(RobotWebApplication.class, args);
    }
}
