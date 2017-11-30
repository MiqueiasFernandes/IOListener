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
public enum DataBits {

    DATABITS_5(5),
    DATABITS_6(6),
    DATABITS_7(7),
    DATABITS_8(8);

    public final int val;

    DataBits(int val) {
        this.val = val;
    }

}