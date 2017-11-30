/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.termica;

import com.mikeias.iolistener.resources.impressao.serial.SerialFactory;
import com.mikeias.iolistener.resources.impressao.serial.SerialConfig;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Arrays;

/**
 *
 * @author mfernandes
 */
public class TermicPrinterSerial extends TermicPrinter {

    SerialPort port;

    @Override
    public void imprime() throws Exception {
        if (port != null) {
            port.openPort();
            port.getOutputStream().write(getBytes());
            port.closePort();
        }
        System.err.println(new String(getBytes()));
        throw new Exception("Impressora não selecionada!");
    }

    @Override
    public void selecionaImpressora(String impressoraSelecionada) throws Exception {
        String porta = impressoraSelecionada;
        boolean encontrou = false;
        for (SerialPort serial : SerialPort.getCommPorts()) {
            if ((serial.getDescriptivePortName() + serial.getSystemPortName() + serial.toString())
                    .toLowerCase().contains(impressoraSelecionada.toLowerCase())) {
                porta = serial.getSystemPortName();
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            throw new Exception("impressora nao encontrada " + impressoraSelecionada + " em " + Arrays.toString(SerialPort.getCommPorts()));
        }
        port = SerialFactory.jSerialPort(porta, SerialConfig.CONFIG_9600_8N1());

    }

}
