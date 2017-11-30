/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.serial;

/**
 *
 * @author mfernandes
 */
public enum Baud {

    BAUD_75(75),
    BAUD_110(110),
    BAUD_300(300),
    BAUD_1200(1200),
    BAUD_2400(2400),
    BAUD_4800(4800),
    BAUD_9600(9600),
    BAUD_19200(19200),
    BAUD_38400(38400),
    BAUD_57600(57600),
    BAUD_115200(115200);

    public final int val;

    Baud(int val) {
        this.val = val;
    }

}