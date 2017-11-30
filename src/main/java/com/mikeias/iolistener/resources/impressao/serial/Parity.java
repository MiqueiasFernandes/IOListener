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
public enum Parity {

    PARITY_NONE(SerialPort.NO_PARITY),
    PARITY_ODD(SerialPort.ODD_PARITY),
    PARITY_EVEN(SerialPort.EVEN_PARITY),
    PARITY_MARK(SerialPort.MARK_PARITY),
    PARITY_SPACE(SerialPort.SPACE_PARITY);

    public final int val;

    Parity(int val) {
        this.val = val;
    }

}
