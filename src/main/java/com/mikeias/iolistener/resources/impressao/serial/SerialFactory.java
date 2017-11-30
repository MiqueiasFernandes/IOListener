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
public enum SerialFactory {

    ; // Static Factory

       
        
    public static SerialPort jSerialPort(String portName, SerialConfig config) {

        SerialPort serialPort = SerialPort.getCommPort(portName);

        serialPort.setFlowControl(config.getFlowControl().val);
        serialPort.setComPortParameters(
                config.getBaud().val,
                config.getDataBits().val,
                config.getStopBits().val,
                config.getParity().val
        );

        return serialPort;
    }

}
