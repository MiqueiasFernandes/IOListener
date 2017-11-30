/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.serial;

import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author mfernandes
 */
public enum FlowControl {

    FLOWCONTROL_NONE(SerialPort.FLOW_CONTROL_DISABLED),
    FLOWCONTROL_RTSCTS_IN(SerialPort.FLOW_CONTROL_RTS_ENABLED | SerialPort.FLOW_CONTROL_CTS_ENABLED),
    FLOWCONTROL_RTSCTS_OUT(SerialPort.FLOW_CONTROL_DSR_ENABLED | SerialPort.FLOW_CONTROL_DTR_ENABLED),
    FLOWCONTROL_XONXOFF_IN(SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED),
    FLOWCONTROL_XONXOFF_OUT(SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED);

    public final int val;

    FlowControl(int val) {
        this.val = val;
    }

}