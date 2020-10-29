package com.levent8421.robot.web.ws;

import lombok.Data;

/**
 * Create By leven ont 2020/10/29 21:58
 * Class Name :[RobotControlMessage]
 * <p>
 * 机器人控制消息
 *
 * @author leven
 */
@Data
public class RobotControlMessage {
    private String action;
    private Integer motor;
    private Integer speed;
    private Boolean state;
}
