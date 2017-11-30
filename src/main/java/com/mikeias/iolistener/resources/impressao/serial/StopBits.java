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
public enum StopBits {

    STOPBITS_1(SerialPort.ONE_STOP_BIT),
    STOPBITS_2(SerialPort.TWO_STOP_BITS),
    STOPBITS_1_5(SerialPort.ONE_POINT_FIVE_STOP_BITS);

    public final int val;

    StopBits(int val) {
        this.val = val;
    }

}
