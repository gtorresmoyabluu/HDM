/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.command;

/**
 *
 * @author Gonzalo Torres
 */
public class ZeroTouchCommand extends Command {

    public int orderCmd = 0;

    public ZeroTouchCommand() {
        this.type = Command.TYPE_ZERO_TOUCH;
    }

    @Override
    public boolean executeCommand() throws Exception {
        CommandRequestFactory.addCommand(this);
        return errorCheck;
    }

    @Override
    public void receiveError(String errorString) {
        this.errorCheck = true;
        this.errorString = errorString;
        CommandRequestFactory.removeCommand(this);
        sb.setLength(0);
        sb.append("*******************************************************")
                .append("\n* receiveError: ")
                .append(errorString)
                .append("\n*************************************************************");
        logger.error(sb);
    }
}
