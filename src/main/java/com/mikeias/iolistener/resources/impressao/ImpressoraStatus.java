/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;

/**
 *
 * @author mfernandes
 */
public class ImpressoraStatus {

    PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
    PrintService[] termicas = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
    PrintService[] defaults = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);

    boolean isTermica(PrintService service) {
        for (PrintService def : defaults) {
            if (def.getName().equalsIgnoreCase(service.getName())) {
                return false;
            }
        }
        for (PrintService termica : termicas) {
            if (termica.getName().equalsIgnoreCase(service.getName())) {
                return true;
            }
        }
        return false;
    }

    String htmlTest() {
        StringBuilder sb = new StringBuilder("<ul>");

        for (PrintService ps : prints) {

            sb.append("<li style=\"border-bottom: 3px solid lightgray;padding: 20px;\">");
            boolean termic = isTermica(ps);
            if (termic) {
                sb.append("<img width=\"80\" src='data:image/jpeg;base64,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' />");
            } else {
                sb.append("<img  width=\"130\"  src='data:image/jpeg;base64,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' />");
            }

            sb.append(ps.getName() + " \n"
                    + "<span style=\"border: 1px solid;border-radius: 20px;padding: 5px;\"> status: "
                    + getStatusPrinter(ps) + "</span> \n"
                    + "<button  onClick=\"window.location.href = 'test/" + (termic ? "termic/" : "default/") + ps.getName() + "'\">Testar</button> <br>\n"
                    + "<code>code for connect this printer</code>");

            sb.append("</li>");

        }

        return sb.append("</ul>").toString();
    }

    String getStatusPrinter(PrintService print) {
        for (Attribute attribute : print.getAttributes().toArray()) {
            if (attribute.getName().equalsIgnoreCase("printer-is-accepting-jobs")) {
                if (attribute.toString().equalsIgnoreCase("accepting-jobs")) {
                    return "ok";
                } else {
                    return attribute.toString();
                }
            }
        }
        return "error";
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");
        boolean status = false;
        for (PrintService print : prints) {

            sb
                    .append("{\"nome\":\"")
                    .append(print.getName())
                    .append("\",\"atributes\":[");

            for (Attribute attribute : print.getAttributes().toArray()) {
                sb
                        .append("\"")
                        .append(attribute.getName())
                        .append("\",\"")
                        .append(attribute)
                        .append("\",");
                if (attribute.getName().equalsIgnoreCase("printer-is-accepting-jobs") && attribute.toString().equalsIgnoreCase("accepting-jobs")) {
                    status = true;
                }
            }
            sb
                    .deleteCharAt(sb.toString().length() - 1)
                    .append("],\"status\":")
                    .append(status)
                    .append(",\"termica\":")
                    .append(isTermica(print))
                    .append("},");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");
        return sb.toString();
    }

}