/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.termica;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author mfernandes
 */
public class TermicPrinterDefault extends TermicPrinter {

    private static PrintService impressora; // O Atributo citado anteriormente

    @Override
    public void selecionaImpressora(String impressoraSelecionada) throws Exception {  //Passa por parâmetro o nome salvo da impressora
        DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
        for (PrintService p : ps) {
            if (p.getName() != null && p.getName().toLowerCase().contains(impressoraSelecionada.toLowerCase())) {
                impressora = p;
                return;
            }
        }
        throw new Exception("Impressora não encontrada" + impressoraSelecionada + " en " + Arrays.toString(ps));
    }

    @Override
    public void imprime() throws Exception {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        DocPrintJob dpj = impressora.createPrintJob();
        Doc doc = new SimpleDoc(new ByteArrayInputStream(getBytes()), flavor, null);
        dpj.print(doc, null);

        dpj = impressora.createPrintJob();
        doc = new SimpleDoc(
                "<span ******NAO E DOCUMENTO FISCAL2******\n"
                + "IMPERIO DOS ESPETOS\n"
                + "MOV.: 24/11/17 - IMP.: 25/11/17 - 00:34H\n"
                + "OPER.: MIRELLE - GARCON: MIRELLE GA\n"
                + "MESA: 10 SEQ: 36\n"
                + "------------------------------------------\n"
                + "------------------------------------------\n"
                + "QTDE.   DESCRICAO      UNIT.         VALOR\n"
                + "------------------------------------------\n"
                + "2       CORACAO DE BOI  4,37          8,74\n\n\n"
                + "SUBTOTAL   :             26,73\n"
                + "SERVICO   :       2,67\n"
                + "TOTAL:                    29,40\n"
                + "------------------------------------------\n"
                + "TEMPO  DE PERMANENCIA: 00:38:24\n"
                + "NO. PESSOA(S): 1\n"
                + "POR PESS.: 29,40\n"
                + "BEM VINDO AO IMPERIO\n"
                + "SISTEMA E-RESTAURANTE\n"
                + "******NAO E DOCUMENTO FISCAL******\n",
                DocFlavor.STRING.TEXT_HTML, null);
        dpj.print(doc, null);

        dpj = impressora.createPrintJob();
        doc = new SimpleDoc(new ByteArrayInputStream(
                ("******NAO E DOCUMENTO FISCAL3******\n"
                        + "IMPERIO DOS ESPETOS\n"
                        + "MOV.: 24/11/17 - IMP.: 25/11/17 - 00:34H\n"
                        + "OPER.: MIRELLE - GARCON: MIRELLE GA\n"
                        + "MESA: 10 SEQ: 36\n"
                        + "------------------------------------------\n"
                        + "------------------------------------------\n"
                        + "QTDE.   DESCRICAO      UNIT.         VALOR\n"
                        + "------------------------------------------\n"
                        + "2       CORACAO DE BOI  4,37          8,74\n\n\n"
                        + "SUBTOTAL   :             26,73\n"
                        + "SERVICO   :       2,67\n"
                        + "TOTAL:                    29,40\n"
                        + "------------------------------------------\n"
                        + "TEMPO  DE PERMANENCIA: 00:38:24\n"
                        + "NO. PESSOA(S): 1\n"
                        + "POR PESS.: 29,40\n"
                        + "BEM VINDO AO IMPERIO\n"
                        + "SISTEMA E-RESTAURANTE\n"
                        + "******NAO E DOCUMENTO FISCAL******\n").getBytes()
        ), flavor, null);
        dpj.print(doc, null);
    }

}