/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources;

import com.fazecast.jSerialComm.SerialPort;
import groovy.json.JsonOutput;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.print.Pageable;
import java.awt.print.Printable;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import static javax.print.attribute.standard.MediaSizeName.A;
import static javax.print.attribute.standard.MediaSizeName.B;
import static javax.print.attribute.standard.MediaSizeName.C;
import static javax.print.attribute.standard.MediaSizeName.D;
import static javax.print.attribute.standard.MediaSizeName.E;
import static javax.print.attribute.standard.MediaSizeName.EXECUTIVE;
import static javax.print.attribute.standard.MediaSizeName.FOLIO;
import static javax.print.attribute.standard.MediaSizeName.INVOICE;
import static javax.print.attribute.standard.MediaSizeName.ISO_A0;
import static javax.print.attribute.standard.MediaSizeName.ISO_A1;
import static javax.print.attribute.standard.MediaSizeName.ISO_A10;
import static javax.print.attribute.standard.MediaSizeName.ISO_A2;
import static javax.print.attribute.standard.MediaSizeName.ISO_A3;
import static javax.print.attribute.standard.MediaSizeName.ISO_A4;
import static javax.print.attribute.standard.MediaSizeName.ISO_A5;
import static javax.print.attribute.standard.MediaSizeName.ISO_A6;
import static javax.print.attribute.standard.MediaSizeName.ISO_A7;
import static javax.print.attribute.standard.MediaSizeName.ISO_A8;
import static javax.print.attribute.standard.MediaSizeName.ISO_A9;
import static javax.print.attribute.standard.MediaSizeName.ISO_B0;
import static javax.print.attribute.standard.MediaSizeName.ISO_B1;
import static javax.print.attribute.standard.MediaSizeName.ISO_B10;
import static javax.print.attribute.standard.MediaSizeName.ISO_B2;
import static javax.print.attribute.standard.MediaSizeName.ISO_B3;
import static javax.print.attribute.standard.MediaSizeName.ISO_B4;
import static javax.print.attribute.standard.MediaSizeName.ISO_B5;
import static javax.print.attribute.standard.MediaSizeName.ISO_B6;
import static javax.print.attribute.standard.MediaSizeName.ISO_B7;
import static javax.print.attribute.standard.MediaSizeName.ISO_B8;
import static javax.print.attribute.standard.MediaSizeName.ISO_B9;
import static javax.print.attribute.standard.MediaSizeName.ISO_C0;
import static javax.print.attribute.standard.MediaSizeName.ISO_C1;
import static javax.print.attribute.standard.MediaSizeName.ISO_C2;
import static javax.print.attribute.standard.MediaSizeName.ISO_C3;
import static javax.print.attribute.standard.MediaSizeName.ISO_C4;
import static javax.print.attribute.standard.MediaSizeName.ISO_C5;
import static javax.print.attribute.standard.MediaSizeName.ISO_C6;
import static javax.print.attribute.standard.MediaSizeName.ISO_DESIGNATED_LONG;
import static javax.print.attribute.standard.MediaSizeName.ITALY_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.JAPANESE_DOUBLE_POSTCARD;
import static javax.print.attribute.standard.MediaSizeName.JAPANESE_POSTCARD;
import static javax.print.attribute.standard.MediaSizeName.JIS_B0;
import static javax.print.attribute.standard.MediaSizeName.JIS_B1;
import static javax.print.attribute.standard.MediaSizeName.JIS_B10;
import static javax.print.attribute.standard.MediaSizeName.JIS_B2;
import static javax.print.attribute.standard.MediaSizeName.JIS_B3;
import static javax.print.attribute.standard.MediaSizeName.JIS_B4;
import static javax.print.attribute.standard.MediaSizeName.JIS_B5;
import static javax.print.attribute.standard.MediaSizeName.JIS_B6;
import static javax.print.attribute.standard.MediaSizeName.JIS_B7;
import static javax.print.attribute.standard.MediaSizeName.JIS_B8;
import static javax.print.attribute.standard.MediaSizeName.JIS_B9;
import static javax.print.attribute.standard.MediaSizeName.LEDGER;
import static javax.print.attribute.standard.MediaSizeName.MONARCH_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_10X13_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_10X14_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_10X15_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_5X7;
import static javax.print.attribute.standard.MediaSizeName.NA_6X9_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_7X9_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_8X10;
import static javax.print.attribute.standard.MediaSizeName.NA_9X11_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_9X12_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_LEGAL;
import static javax.print.attribute.standard.MediaSizeName.NA_LETTER;
import static javax.print.attribute.standard.MediaSizeName.NA_NUMBER_10_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_NUMBER_11_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_NUMBER_12_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_NUMBER_14_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.NA_NUMBER_9_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.PERSONAL_ENVELOPE;
import static javax.print.attribute.standard.MediaSizeName.QUARTO;
import static javax.print.attribute.standard.MediaSizeName.TABLOID;
import javax.print.attribute.standard.OrientationRequested;
import static javax.print.attribute.standard.OrientationRequested.LANDSCAPE;
import static javax.print.attribute.standard.OrientationRequested.PORTRAIT;
import static javax.print.attribute.standard.OrientationRequested.REVERSE_LANDSCAPE;
import static javax.print.attribute.standard.OrientationRequested.REVERSE_PORTRAIT;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 *
 * @author mfernandes
 */
@Path("impressora")
public class ImpressoraResource {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String home() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Recursso de Impressoras</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Impressoras em localhost</h1>"
                + "<a href=\"impressora/status\">STATUS</a> <br>"
                + "<a href=\"impressora/test\">TESTAR</a> <br>"
                + "<a href=\"impressora/print\">IMPRIMIR</a>"
                + "</body>\n"
                + "</html>";
    }

    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {
        return new ImpressoraStatus().toString();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    public String test() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Teste de Impressoras</title>\n"
                + "</head>\n"
                + "<body>\n"
                + new ImpressoraStatus().htmlTest()
                + "<br>"
                + "<input type=\"text\" value=\"COM3\"> <button   onClick=\"window.location.href = 'test/serial/' + document.getElementsByTagName('input')[0].value\">Teste Serial</button>"
                + "</body>\n"
                + "</html>";
    }

    @GET
    @Path("test/{tipo}/{printer}")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(
            @PathParam("tipo") String tipo,
            @PathParam("printer") String printer) {

        try {

            switch (tipo) {
                case "termica": {
                    TermicPrinter termicPrinter = new TermicPrinterDefault();
                    termicPrinter.selecionaImpressora(printer);
                    termicPrinter.getDemo();
                    termicPrinter.imprime();
                }
                break;
                case "serial": {
                    TermicPrinter termicPrinter = new TermicPrinterSerial();
                    termicPrinter.selecionaImpressora(printer);
                    termicPrinter.getDemo();
                    termicPrinter.imprime();
                }
                break;
                default: {
                    HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(getMediaByName("iso-a4"));
                    set.add(getOrientationByName("portrait"));
                    PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

                    JTextPane mTextPane = new JTextPane();
                    mTextPane.setContentType("text/html");

                    mTextPane.setText("<hmtl>\n"
                            + "<body>\n"
                            + "\n"
                            + "* Scanned images\n"
                            + "\n"
                            + "<img src='data:image/jpeg;base64,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' />\n"
                            + "<center><b><font face=\"Arial,Helvetica\">OBSERVATIONS</font></b></center>\n"
                            + "<font size=+1>The printer dot size and dot density (graininess) varies\n"
                            + "considerably with these printers.</font>\n"
                            + "<br><font size=+1>In order of the smallest dots/highest density to the\n"
                            + "largest dots/lowest density are:</font>\n"
                            + "<br><font size=+1>Epson 825, Canon 9000, HP-7550, EP-785, EP-CX3200, HP-940C,\n"
                            + "EP-G95, Xerox 2006.</font>\n"
                            + "<br><font size=+1><b>NOTE</b>: This factor is what makes a print look less\n"
                            + "like a digital printer and more like a film printer.</font>\n"
                            + "<p><font size=+1>Printer resolution of the clothes-pin <u>wood-grain</u>\n"
                            + "-from best to worst is:</font>\n"
                            + "<br><font size=+1>HP-940C, EP-825, Canon 9000, HP-7550, EP-785, HP-G95,\n"
                            + "Epson CX3200, Xerox 2006</font>\n"
                            + "<p><font size=+1>Best \"total\" printer resolution of the test target (below)\n"
                            + "is:</font>\n"
                            + "<br><font size=+1>HP-940C, HP-G95, (EP-825, EP-785, Canon 9000, EP-925,\n"
                            + "EP C-80), EP C-82, (EP CX3200 &amp; Xerox 2006)</font>\n"
                            + "<br>(&nbsp; )=All measured the same\n"
                            + "<p><font size=+1>Fastest print time of test target (below) is:</font>\n"
                            + "<br><font size=+1>Canon 9000, HP-7550, HP-G95, Epson CX3200, Epson C-82,\n"
                            + "Epson 825, Epson C-80, Epson C-82 (RPM), HP-940C</font>\n"
                            + "<p><b><i><font color=\"#FF0000\"><font size=+1>Yes, Virginia, it seems there\n"
                            + "really IS no fast, high-resolution, fine-grained printer!</font></font></i></b>\n"
                            + "<p><tt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "LINE WIDTHS/mm</tt>\n"
                            + "<br><tt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "RESOLUTION&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "SELECTED</tt>\n"
                            + "<br><tt>Printer (Inks) Vert Horiz TOTAL PRINT-TIME RESOLUTION&nbsp;&nbsp;&nbsp;\n"
                            + "MODE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; COST&nbsp;&nbsp; \"$/LW\"&nbsp; SUBMITTER</tt>\n"
                            + "<br><tt>-------------------------------------------------------------------------------------------</tt>\n"
                            + "<center><tt>\"Photo Quality\" Printers<b>#</b></tt></center>\n"
                            + "<tt>Canon 9000&nbsp; (6) 13&nbsp;&nbsp; 14&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "0' 46\"&nbsp;&nbsp; 2400x1200&nbsp;&nbsp;&nbsp; Custom-Fine&nbsp; $250&nbsp;\n"
                            + "$9.25&nbsp;&nbsp; Rafe</tt>\n"
                            + "<br><tt>Epson 785*&nbsp; (6) 14&nbsp;&nbsp; 13&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "?&nbsp;&nbsp;&nbsp;&nbsp; 2880x720&nbsp;&nbsp;&nbsp;&nbsp; High Quality\n"
                            + "$150&nbsp; $5.55&nbsp;&nbsp; Joe</tt>\n"
                            + "<br><tt>Epson 825&nbsp;&nbsp; (6) 14&nbsp;&nbsp; 13&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "3' 25\"&nbsp;&nbsp; 5760x720&nbsp;&nbsp;&nbsp;&nbsp; Best Photo&nbsp;&nbsp;\n"
                            + "$125&nbsp; $4.62&nbsp;&nbsp; Jack Y.</tt>\n"
                            + "<br><tt>Epson 925&nbsp;&nbsp; (4) 14&nbsp;&nbsp; 13&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "?&nbsp;&nbsp;&nbsp;&nbsp; 5760x720&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $250&nbsp; $9.25&nbsp;&nbsp;\n"
                            + "Jack B.</tt>\n"
                            + "<br><tt>Epson CX-3200(4)10&nbsp;&nbsp; 14&nbsp;&nbsp;&nbsp; 24&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "2' 15\"&nbsp;&nbsp; 5700x720&nbsp;&nbsp;&nbsp;&nbsp; Best Photo&nbsp;&nbsp;\n"
                            + "$110&nbsp; $4.58&nbsp;&nbsp; Rick</tt>\n"
                            + "<br>*Forerunner of the Epson Photo 825\n"
                            + "<br><b><tt>#</tt></b> With Epson \"Photo\" printers, the printed picture\n"
                            + "quality is much superior to their \"Color\" printers.&nbsp; Most likely this\n"
                            + "is due to the dye ink used.\n"
                            + "<p><tt>HP-940C&nbsp;&nbsp;&nbsp;&nbsp; (4) 15&nbsp;&nbsp; 17&nbsp;&nbsp;&nbsp;\n"
                            + "32&nbsp;&nbsp;&nbsp;&nbsp; 6' 23\"&nbsp;&nbsp; 2400x1200&nbsp; ColorSmart\n"
                            + "III $145&nbsp; $4.53&nbsp;&nbsp; Allory</tt>\n"
                            + "<br><tt>HP-7550&nbsp;&nbsp;&nbsp;&nbsp; (7) 14&nbsp;&nbsp; 14&nbsp;&nbsp;&nbsp;\n"
                            + "28&nbsp;&nbsp;&nbsp;&nbsp; 0' 56\"&nbsp;&nbsp; 4800x1200&nbsp;&nbsp;&nbsp;\n"
                            + "Normal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $280 $10.00&nbsp;&nbsp; Al R.</tt>\n"
                            + "<br><tt>HP-7550&nbsp;&nbsp;&nbsp;&nbsp; (7) 14&nbsp;&nbsp; 14&nbsp;&nbsp;&nbsp;\n"
                            + "28&nbsp;&nbsp;&nbsp;&nbsp; 2' 25\"&nbsp;&nbsp; 4800x1200&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "Best&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $280 $10.00&nbsp;&nbsp;\n"
                            + "Al R.</tt>\n"
                            + "<br><tt>HP-G95&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (4) 15&nbsp;&nbsp; 15&nbsp;&nbsp;&nbsp;\n"
                            + "30&nbsp;&nbsp;&nbsp;&nbsp; 1' 40\"&nbsp;&nbsp; 2400x1200&nbsp;&nbsp; Best\n"
                            + "(Photo)&nbsp; $800 $26.67&nbsp;&nbsp; Rick</tt>\n"
                            + "<br><tt>-------------------------------------------------------------------------------------------</tt>\n"
                            + "<center>\n"
                            + "<p><tt>\"Color\" Printers</tt></center>\n"
                            + "<tt>Epson C-80&nbsp; (4) 10&nbsp;&nbsp; 17&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "4' 37\"&nbsp;&nbsp; 2880x720&nbsp;&nbsp; Photo-2880dpi&nbsp; $115&nbsp;\n"
                            + "$4.25&nbsp;&nbsp; Jack Y.</tt>\n"
                            + "<br><tt>Epson C-82&nbsp; (4)&nbsp; 9&nbsp;&nbsp; 17&nbsp;&nbsp;&nbsp; 26&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "2' 30\"&nbsp;&nbsp; 5760x1440&nbsp;&nbsp; Best Photo&nbsp;&nbsp;&nbsp; $100&nbsp;\n"
                            + "$3.84&nbsp;&nbsp; Jack Y.</tt>\n"
                            + "<br><tt>Epson C-82**(4) 10&nbsp;&nbsp; 17&nbsp;&nbsp;&nbsp; 27&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "4' 40\"&nbsp;&nbsp; 5760x1440&nbsp;&nbsp;&nbsp; Photo RPM&nbsp;&nbsp;&nbsp;\n"
                            + "$100&nbsp; $3.70&nbsp;&nbsp; Jack Y.</tt>\n"
                            + "<br>(** with \"microweave\" and \"RPM\" both turned on, the print took 10 min,\n"
                            + "20 sec -and had no significant improvement in resolution.)\n"
                            + "<p><tt>Xerox 2006&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 13&nbsp;&nbsp; 11&nbsp;&nbsp;&nbsp;\n"
                            + "24&nbsp;&nbsp;&nbsp;&nbsp; 4' 38\"##&nbsp; 600x600&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $11,000&nbsp;&nbsp;&nbsp; !&nbsp;&nbsp;&nbsp;\n"
                            + "Allory</tt>\n"
                            + "<br>##&nbsp; First print only, second print in 2 seconds\n"
                            + "<p><tt>Lexmark Z23&nbsp;&nbsp;&nbsp;&nbsp; 10&nbsp;&nbsp; 10&nbsp;&nbsp;&nbsp;\n"
                            + "20&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ?&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "1200x1200&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                            + "$40&nbsp; $2.00&nbsp;&nbsp; Dale</tt>\n"
                            + "<p><b><font face=\"Arial,Helvetica\"><a href=\"mailto:jack@finalapproach.net\">Jack\n"
                            + "Yeazel</a></font></b>\n"
                            + "<br>&nbsp;\n"
                            + "\n"
                            + "<img  width=\"130\"  src='data:image/jpeg;base64,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' />\n"
                            + "</body>\n"
                            + "</html>");

                    PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
                    preview.selecionaImpressora(printer);

                    return preview.printPages("all", false);
                }
            }
            return "{\"error\":\"\"}";

        } catch (Exception ex) {
            return "{\"error\":\"" + ex.toString() + "\"}";
        }
    }

    @GET
    @Path("print")
    @Produces(MediaType.TEXT_HTML)
    public String print() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Simple Jersey</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <form action=\"http://localhost:9090/impressora/print/iso-a4/portrait\" method=\"post\">\n"
                + "    <p>Text: <input type=\"text\" name=\"content\"></p>\n"
                + "    <p>Pages: <input type=\"text\" name=\"pages\"></p>\n"
                + "<button type=\"submit\">enviar</button>"
                + "  </form>\n"
                + "</body>\n"
                + "</html>";
    }

    @POST
    @Path("print/{media}/{orientation}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMethod(
            @PathParam("media") String media,
            @PathParam("orientation") String orientation,
            @FormParam("pages") String pages,
            @FormParam("dialog") String dialog,
            @FormParam("printer") String printer,
            @FormParam("tipo") String tipo,
            @FormParam("content") String content) {

        try {

            switch (tipo) {
                case "termica": {
                    TermicPrinter termicPrinter = new TermicPrinterDefault();
                    termicPrinter.selecionaImpressora(printer);
                    termicPrinter.print(content);
                }
                break;
                case "serial": {
                    TermicPrinter termicPrinter = new TermicPrinterSerial();
                    termicPrinter.selecionaImpressora(printer);
                    termicPrinter.print(content);
                }
                break;
                default: {
                    HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(getMediaByName(media));
                    set.add(getOrientationByName(orientation));
                    PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

                    JTextPane mTextPane = new JTextPane();
                    mTextPane.setContentType("text/html");

                    mTextPane.setText(content);

                    PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
                    preview.selecionaImpressora(printer);

                    if (pages == null || pages.isEmpty()) {
                        return JsonOutput.toJson(preview.getImagesOfPages());
                    }
                    return preview.printPages(pages, "true".equalsIgnoreCase(dialog));
                }
            }
            return "{\"error\":\"\"}";

        } catch (Exception ex) {
            return "{\"error\":\"" + ex.toString() + "\"}";
        }
    }

    public MediaSizeName getMediaByName(String media) {

        String[] myStringTable = {
            "iso-a0",
            "iso-a1",
            "iso-a2",
            "iso-a3",
            "iso-a4",
            "iso-a5",
            "iso-a6",
            "iso-a7",
            "iso-a8",
            "iso-a9",
            "iso-a10",
            "iso-b0",
            "iso-b1",
            "iso-b2",
            "iso-b3",
            "iso-b4",
            "iso-b5",
            "iso-b6",
            "iso-b7",
            "iso-b8",
            "iso-b9",
            "iso-b10",
            "jis-b0",
            "jis-b1",
            "jis-b2",
            "jis-b3",
            "jis-b4",
            "jis-b5",
            "jis-b6",
            "jis-b7",
            "jis-b8",
            "jis-b9",
            "jis-b10",
            "iso-c0",
            "iso-c1",
            "iso-c2",
            "iso-c3",
            "iso-c4",
            "iso-c5",
            "iso-c6",
            "na-letter",
            "na-legal",
            "executive",
            "ledger",
            "tabloid",
            "invoice",
            "folio",
            "quarto",
            "japanese-postcard",
            "oufuko-postcard",
            "a",
            "b",
            "c",
            "d",
            "e",
            "iso-designated-long",
            "italian-envelope",
            "monarch-envelope",
            "personal-envelope",
            "na-number-9-envelope",
            "na-number-10-envelope",
            "na-number-11-envelope",
            "na-number-12-envelope",
            "na-number-14-envelope",
            "na-6x9-envelope",
            "na-7x9-envelope",
            "na-9x11-envelope",
            "na-9x12-envelope",
            "na-10x13-envelope",
            "na-10x14-envelope",
            "na-10x15-envelope",
            "na-5x7",
            "na-8x10",};

        MediaSizeName[] myEnumValueTable = {
            ISO_A0,
            ISO_A1,
            ISO_A2,
            ISO_A3,
            ISO_A4,
            ISO_A5,
            ISO_A6,
            ISO_A7,
            ISO_A8,
            ISO_A9,
            ISO_A10,
            ISO_B0,
            ISO_B1,
            ISO_B2,
            ISO_B3,
            ISO_B4,
            ISO_B5,
            ISO_B6,
            ISO_B7,
            ISO_B8,
            ISO_B9,
            ISO_B10,
            JIS_B0,
            JIS_B1,
            JIS_B2,
            JIS_B3,
            JIS_B4,
            JIS_B5,
            JIS_B6,
            JIS_B7,
            JIS_B8,
            JIS_B9,
            JIS_B10,
            ISO_C0,
            ISO_C1,
            ISO_C2,
            ISO_C3,
            ISO_C4,
            ISO_C5,
            ISO_C6,
            NA_LETTER,
            NA_LEGAL,
            EXECUTIVE,
            LEDGER,
            TABLOID,
            INVOICE,
            FOLIO,
            QUARTO,
            JAPANESE_POSTCARD,
            JAPANESE_DOUBLE_POSTCARD,
            A,
            B,
            C,
            D,
            E,
            ISO_DESIGNATED_LONG,
            ITALY_ENVELOPE,
            MONARCH_ENVELOPE,
            PERSONAL_ENVELOPE,
            NA_NUMBER_9_ENVELOPE,
            NA_NUMBER_10_ENVELOPE,
            NA_NUMBER_11_ENVELOPE,
            NA_NUMBER_12_ENVELOPE,
            NA_NUMBER_14_ENVELOPE,
            NA_6X9_ENVELOPE,
            NA_7X9_ENVELOPE,
            NA_9X11_ENVELOPE,
            NA_9X12_ENVELOPE,
            NA_10X13_ENVELOPE,
            NA_10X14_ENVELOPE,
            NA_10X15_ENVELOPE,
            NA_5X7,
            NA_8X10,};

        int pos = 0;
        for (String s : myStringTable) {
            if (s.equalsIgnoreCase(media)) {
                return myEnumValueTable[pos];
            }
            pos++;
        }
        return null;
    }

    public OrientationRequested getOrientationByName(String orientation) {
        String[] myStringTable = {
            "portrait",
            "landscape",
            "reverse-landscape",
            "reverse-portrait"
        };

        OrientationRequested[] myEnumValueTable = {
            PORTRAIT,
            LANDSCAPE,
            REVERSE_LANDSCAPE,
            REVERSE_PORTRAIT
        };
        int pos = 0;
        for (String s : myStringTable) {
            if (s.equalsIgnoreCase(orientation)) {
                return myEnumValueTable[pos];
            }
            pos++;
        }

        return null;

    }
}

class PrintPreview extends JScrollPane {

    private static final long serialVersionUID = 1L;
    private Pageable mPageable = null;
    private double mScale = 1.0;
    private Page mPages[] = null;
    private CardLayout mCardLayout = new CardLayout();
    private JPanel mMainPanel = new JPanel(mCardLayout);
    private int mCurrentPage = 1;

    private JPanel mControls = null;
    private JButton mPrevButton = null;
    private JButton mNextButton = null;
    private JComboBox<String> mPagesCombo = null;
    private JSlider mZoomSlider = null;

    public PrintPreview(Pageable pg) {
        this.mPageable = pg;
        createPreview();
    }

    public PrintPreview(final Printable pr, final PageFormat p) {
        this.mPageable = new Pageable() {
            public int getNumberOfPages() {
                Graphics g = new java.awt.image.BufferedImage(2, 2,
                        java.awt.image.BufferedImage.TYPE_INT_RGB)
                        .getGraphics();
                int n = 0;
                try {
                    while (pr.print(g, p, n) == Printable.PAGE_EXISTS) {
                        n++;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return n;
            }

            public PageFormat getPageFormat(int x) {
                return p;
            }

            public Printable getPrintable(int x) {
                return pr;
            }
        };
        createPreview();
    }

    private void createPreview() {
        mPages = new Page[mPageable.getNumberOfPages()];
        PageFormat pf = mPageable.getPageFormat(0);
        Dimension size = new Dimension((int) pf.getPaper().getWidth(), (int) pf
                .getPaper().getHeight());
        if (pf.getOrientation() != PageFormat.PORTRAIT) {
            size = new Dimension(size.height, size.width);
        }

        for (int i = 0; i < mPages.length; i++) {
            mPages[i] = new Page(i, size);
            mMainPanel.add(String.valueOf(i + 1), mPages[i]);
        }

        setViewportView(mMainPanel);
        mPages[mCurrentPage - 1].refreshScale();
    }

    public ArrayList<String> getImagesOfPages() {
        createPreview();

        ArrayList<String> pages = new ArrayList<>();

        for (Page mPage : mPages) {
            mPage.refreshScale();
            pages.add(mPage.imgToBase64String());
        }

        return pages;

    }

    private void setupControls() {

        mControls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mPrevButton = new JButton("<<");
        mNextButton = new JButton(">>");
        mPagesCombo = new JComboBox<String>();
        mZoomSlider = new JSlider();
        for (int i = 0; i < mPages.length; i++) {
            mPagesCombo.addItem(String.valueOf(i + 1));
        }

        mPrevButton.setEnabled(false);
        mNextButton.setEnabled(mPages.length > 1);

        mPrevButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changePage(mCurrentPage - 1);
            }
        });

        mNextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(mCurrentPage + 1);
            }
        });

        mPagesCombo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent arg0) {
                changePage(Integer.parseInt((String) mPagesCombo.getSelectedItem()));
            }
        });

        mZoomSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                double v = (double) mZoomSlider.getValue() / 10.0;
                zoom(v);
            }
        });

        mZoomSlider.setPaintTicks(true);
        //mZoomSlider.setPaintLabels(true);
        mZoomSlider.setMinimum(0);
        mZoomSlider.setMaximum(70);
        mZoomSlider.setValue(10);
        mZoomSlider.setSnapToTicks(true);
        mZoomSlider.setMinorTickSpacing(5);
        mZoomSlider.setMajorTickSpacing(10);

        mControls.add(mPrevButton);
        mControls.add(mPagesCombo);
        mControls.add(mNextButton);
        mControls.add(mZoomSlider);
    }

    /**
     * Method lazy initializes JPanel with controls (if not initialized yet) and
     * returns it
     *
     * @return panel with controls
     */
    public JPanel getControls() {
        if (mControls == null) {
            setupControls();
        }

        return mControls;
    }

    /**
     * Method changes current page, updates controls (if displayed) and returns
     * true on success This method is safe, it checks if passed page number
     * exists.
     *
     * @param page number
     * @return true if page was changed
     */
    public boolean changePage(int page) {

        if (mPages.length < page || page < 1 || mCurrentPage == page) {
            return false;
        }

        mCardLayout.show(mMainPanel, String.valueOf(page));
        getCurrentPageComponent().refreshScale();
        mCurrentPage = page;
        if (mPrevButton != null && mNextButton != null) {
            mPrevButton.setEnabled(mCurrentPage == 1 ? false : true);
            mNextButton.setEnabled(mPages.length == page ? false : true);
        }
        if (mPagesCombo != null) {
            mPagesCombo.setSelectedItem(String.valueOf(page));
        }

        validate();
        return true;
    }

    /**
     * Method checks if next page exists before processing
     *
     * @return number of pages
     */
    public int pages() {
        return mPages.length;
    }

    /**
     * Method checks if previous page exists before processing.
     *
     * @return true if page changed
     */
    public boolean previousPage() {
        return changePage(mCurrentPage - 1);
    }

    /**
     * @return true if page changed
     */
    public boolean nextPage() {
        return changePage(mCurrentPage + 1);
    }

    PrintService printService;

    public void selecionaImpressora(String impressoraSelecionada) throws Exception {
        for (PrintService ps : PrintServiceLookup.lookupPrintServices(null, null)) {
            if (ps.getName().toLowerCase().contains(impressoraSelecionada.toLowerCase())) {
                this.printService = ps;
                return;
            }
        }
        throw new Exception("Impressora " + impressoraSelecionada + " não encontrada em " + Arrays.toString(PrintServiceLookup.lookupPrintServices(null, null)));
    }

    /**
     * Prints whole document
     */
    public String print(boolean dialog) {
        try {
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.defaultPage(mPageable.getPageFormat(0));
            pj.setPageable(mPageable);
            pj.setPrintService(printService);
            if (!dialog || pj.printDialog()) {
                pj.print();
            }
            return "sucesso";
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    /**
     * Prints currently selected page
     */
    public String printCurrentPage(boolean dialog) {
        try {
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.defaultPage(mPageable.getPageFormat(0));
            pj.setPrintable(new PsuedoPrintable());
            pj.setPrintService(printService);
            javax.print.attribute.HashPrintRequestAttributeSet pra = new javax.print.attribute.HashPrintRequestAttributeSet();
            if (!dialog || pj.printDialog(pra)) {
                pj.print(pra);
            }
            return "sucesso";
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    /**
     * Returns currently selected Page component
     *
     * @return Page component
     */
    public Page getCurrentPageComponent() {
        return mPages[mCurrentPage - 1];
    }

    /**
     * Return currently selected page's number
     *
     * @return current page number
     */
    public int getCurrentPage() {
        return mCurrentPage;
    }

    /**
     *
     *
     * @param zoom double value greater than 0
     */
    public void zoom(double zoom) {
        double temp = zoom;
        if (temp == mScale) {
            return;
        }
        if (temp == 0) {
            temp = 0.01;
        }

        mScale = temp;
        getCurrentPageComponent().refreshScale();
        this.validate();
    }

    String printPages(String pages, boolean dialog) {

        try {

            boolean[] paginas = new boolean[mPages.length];

            for (int i = 0; i < mPages.length; i++) {
                paginas[i] = false;
            }

            if ("all".equals(pages)) {
                for (int i = 0; i < mPages.length; i++) {
                    paginas[i] = true;
                }
            } else {

                String[] secs = pages.split(",");

                for (String sec : secs) {
                    String[] intervalo = sec.split("-");
                    paginas[Integer.parseInt(intervalo[0]) - 1] = true;
                    if (intervalo.length > 1) {
                        for (int i = Integer.parseInt(intervalo[0]); i < Integer.parseInt(intervalo[1]); i++) {
                            paginas[i] = true;
                        }
                    }
                }
            }

            for (int i = 0; i < mPages.length; i++) {
                if (!paginas[i]) {
                    break;
                } else if (i + 1 == mPages.length) {
                    return print(dialog);
                }
            }

            for (int i = 0; i < mPages.length; i++) {
                if (paginas[i]) {
                    changePage(i);
                    String result = printCurrentPage(dialog);
                    if (!"sucesso".equalsIgnoreCase(result)) {
                        return result;
                    }
                }
            }
            return "sucesso";
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    class Page extends JLabel {

        private static final long serialVersionUID = 1L;
        private final int mPageNum;
        private final PageFormat mPageFormat;
        private BufferedImage mImage = null;
        private Dimension mSize = null;

        public Page(int x, Dimension size) {
            setHorizontalAlignment(JLabel.CENTER);
            mSize = size;
            mImage = new java.awt.image.BufferedImage(size.width, size.height,
                    java.awt.image.BufferedImage.TYPE_INT_RGB);
            mPageNum = x;
            mPageFormat = mPageable.getPageFormat(mPageNum);

            Graphics g = mImage.getGraphics();
            Color c = g.getColor();
            g.setColor(Color.white);
            g.fillRect(0, 0, (int) mPageFormat.getWidth(), (int) mPageFormat.getHeight());
            g.setColor(c);
            try {
                //g.clipRect(0, 0, (int) pf.getWidth(), (int) pf.getHeight());
                mPageable.getPrintable(mPageNum).print(g, mPageFormat, mPageNum);
                g.setColor(Color.black);
                g.drawRect(0, 0, (int) mPageFormat.getWidth() - 1, (int) mPageFormat.getHeight() - 1);
            } catch (Exception ex) {
            }
            setIcon(new ImageIcon(mImage));
        }

        public String imgToBase64String( //                final RenderedImage img, 
                //                final String formatName
                ) {

            final ByteArrayOutputStream os = new ByteArrayOutputStream();

            try {
                ImageIO.write(mImage, "png", os);
                return Base64.getEncoder().encodeToString(os.toByteArray());
            } catch (final IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        }

        public void refreshScale() {

            if (mScale != 1.0) {
                int w = (int) (mSize.width * mScale);
                int h = (int) (mSize.height * mScale);
                setIcon(new ImageIcon(
                        mImage.getScaledInstance(w, h, BufferedImage.SCALE_FAST)));
            } else {
                setIcon(new ImageIcon(mImage));
            }

            validate();
        }
    }

    class PsuedoPrintable implements Printable {

        public int print(Graphics g, PageFormat fmt, int index) {
            if (index > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            int n = mCurrentPage - 1;
            try {
                return mPageable.getPrintable(n).print(g, fmt, n);
            } catch (Exception ex) {
            }
            return Printable.PAGE_EXISTS;
        }
    }
}

class ImpressoraStatus {

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
                sb.append("<img width=\"80\" src='data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCACgANUDASIAAhEBAxEB/8QAHQABAAAHAQEAAAAAAAAAAAAAAAIDBAUGBwgBCf/EAD4QAAEDAwICCAMECQQDAQAAAAEAAgMEBREGIRIxBxMiQVFhcYEUMqEIUpGxIzNCYnKSosHRFSRDglPh8PH/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAgEDBP/EAB8RAQEBAQACAgMBAAAAAAAAAAABEQISIQNREzFBYf/aAAwDAQACEQMRAD8A6pREQEREBERAREQEREBFBLIyKNz5XtYxoyXOOAB6rBdRdKmm7OXRw1DrjUDbgpRxNz5vPZ/AlBnqlzzRQROknkZHG3cue7AHuVzxqHpovlXxMtkNPbYjydjrZPxIx9Fqq/6uqrnUE3G5VNdNnZrpC/HoOQVTms11VfOlDSloDhJc21Mrf+OlaZSfcdn6rEo+nmzmsLH2i4Npu6QOYXfy5x9VzjE+ol7TmiNp7ju7/AU7BAVeDPJ1xZek3Sd24WxXaKnlP7FUDER7u2/ArL4ZY542yQyMkY4ZDmHIPuuF852VwtN8utml6y1V9VSOzk9TKWg+o5H3WXhuu3EXMVh6ctRW8tZdIqa5xDmXN6qT+Zu39K2jprpn0vd+COtkltdQdsVLcsz5PG344U2WN1sxFJpKmCsgZPSTRTwvGWyRuDmuHkQpywEREAIiICIiAiIgIiICIiAi8JxuVges+kakssTo7ZEa+pyW8Yz1LD4F3efIfiEGbVtXT0NM+orJ44IGDLpJHBrR7lau1V0v0tNxwadp/i5Rt8RMC2Meg5n6LDG0WqNf1YqKqR7qYHZ8nYhj/hHf7ZPis4sPRzabcGvrGmvqBvmUYYD5N/zlXOftOtV19dqjWlQRK+tr252iibwxM9hho9Sobn0a6sZb+uoqeidN/wCAz9sfTh/qXQ0cDIo2xxMayNowGtGAPQL0s7lXpmuLLppzUUVUYr5BU0oz8r2loPp3FVlBaoqVvYYM95XYFVSQ1MToqiJksbubHtDgfYrCr90a2iv4n0QdQzH7naZ/Kf7EKucjL7aAMGe7ZSnx4KzzUGg7xZ+N/UfFU436yDtYHmOYWIyQkuOQchWxans3UpwPernLCAzf5vyVFKzbZZY3VE8KUQfFVLxhSHDyUWN1dNP6jvGnajrrNcaikdnJax3Zd/E07H3C3Lo/p6cCyDVVCCORqqQfVzD+YPstBHbmvC7wypvLdd0af1BatQ0YqbNXQVcXfwO7TfJzTuD6hXVcGWy61torGVdsqp6WpZykieWn027vJb16K+m6e53qisOqGRGaqcIYa2McOXnkHt5bnbIxvjbvUWY2Vv5ERY0REQEREBERAVuvl5obHQPq7nUNhhbyzzcfADvKmXm501ot01bWv4IYxnzce4Ad5K561VUVWr7ya+4zzwU7ezDTsfgMb/k9571XPN6Zbi8X3Xlfqy80drpHGgtlTUMh4OLD5QSB2iPLuHvlbcNLSPpn08sET4X7GMsHDjuGOWFzPR2KS0ator3T1dRVfCu4mwSuG2xGAcef/wCrbdp1/b6yZlPNL8PVO5RSkAn08Vz+adcX0S62CImRMayDhY1ow1gGAB4AKAycH6xuAdsqgprgyVmWkOHeqwVALcN38lM+b7PFOaWu+Upw7KW1jT8o4T4BRkvjGTu3zXbnqdfpmBapbmZU5j2SDLSvXAAEk4HmqYpXRrC9b2bTZpJKi7SRUEjs4mZhr3H+H9r8Mqn1fr3qTJSWHhkkGzqpwy1p/dH7R8+XqtSXOWorKl89bNJPO7m+RxJKqaxZ7lHG2rlZRSOlpw7DJZGcBcPHhycK3PjO/Gcq7SDmFRys5q2Lc9jRyCkPCr3Que7haCT5KguM9Lb2k1Mo4/uN5pchPaQ5hVFVVMVODxvAPgrVcL7LOSynHVR+XNWd73POXuJPmuV7+lyLpV3dz8tiGB4qntVZNT3iiqmOPWwzskaR3EOBH5Kgyr1omhfdNYWSijY55nrYWEAZ2Lxk+mFG6p9GWnLQV6gRYCIiAiIgKGR7Y43PkIaxoySTgAeKiWsumLU5oqdlmpX4lnbxzkHkzub7/kPNBjOvdT/65ceCJx+BgJETO5x++f7eSxul6ysrYKaItEkz2xtLjgZJxurR1+c7oJCCDxHIOQQeSqdMxnl80bW2+IS0c3x4Ay9jWcMg8w3JyPr6rDqmlpa+MsqomvA8RuD/AJWWae13LRt4LnE6q4W4ZK04f6O8eQ35+qxq7XJ9yuFTWvYGGV+SGjYbYA/AKvNmFqrLpY8toquSppwdoZ3cRaPAHn9Vmlj13TTPbFWE0s3IiTYex/zhYCJxzypU0sU/Ze0OAUdfHx1/htjfVJco5WgseCPVTb1c46CwV9XIGlkUL3YPInGw/FaJttxrbY4fA1LuqH/DJu328PbCuWstYVFx0FcqFkErK54ZwhvaDgHtJwfQFcfxdcVWyotPawrrcWiCbrIR/wAMhyB6HuWQ6g1bUX6hZTUsb6WncP0++8h+6CP2fzWmtBTTXOctLssj+c+C2hFExsYaNgAvd6s1yWqaABmANgrPWQ7lZTNGCNgrbU0oMb3vLWRtGXPccNHus/TWKywk8gVTVjYKGAzXCZsEeNgfmd6BUOo9aUVvL4LQ0VFQNjM4dkegWt7lcaq4zumrJnSPPiVN7+mzlf73qx0nFDbGdTFyLz8zlic0j5XF0ji5x7yVAX/d3Utx+8fYLlbqpERePU+Sh7R8B9VL60iRrWtABU5Y1Bw+JJ91sr7OltfX9MFiERe1sDpKh7mkg8LWOOPQnA91rfvXQn2OrT12p77dXN7NLSsp2nzkdn8o/qg6wCIiAiIgIiIIJZGxRPkkIaxgLnE9wC5S1Nen3m/VtfIT+mkLmg/st5NHsAF0T0mVxoNDXaVhw98XUj/uQ38iVzTdbRV0VNBVGN7qaVjXiQDYEgZB8Nz7rP6JYn81EKjzVnM5adygqt+aqTTV7FR5qr66nqquINYykYGBr354icZJPmfJY38T5qNtTtuVuWM1eI5WktbI8hgwCWjfHj6rb0FBpC8WxkFupqOaRjQ1gYTHMPNxGHfitHNnGOa9bUOa4OY4gjkQeST0Vs3WejIrFbzX0de98Qe1vUTgFxycbOGOXPGOQO6w5swcMOwQqCpu9bVwsjq6ueZjPlbJIXAemVJjnLiGg7k4Vysxd7ZZXW/jrrIxkb5DxSUpOI5h5fdd58vHxF+tl0hr2PEfEyWM8MsMg4XxnwcP/ge5Utun4I2tB2Awsf13frdbYmVLXll2jGIJIiOL+F33meIPtg7q7ZPac1lV7vFBYqE1V0m4Gn5Ih88h8vLzWkdY61rtQSGNp+HoQexCw7Y8/FWO8XqsvtW+qr5XPlPMZ2b5AeCtryAuN61UmDnKS4557+SOcT6JhSpCcnyHkvOHCjXjuSCnAzUDyGVPzupMW8zj5KaeaD0LsX7JNp+C6Oamvc3D6+te4HxYwBo/qD1xyCvoN0RWn/ROjPTlCW8L20bJHjwe8cbvq4oMvREQEREBERBrrp3qDT6F4t+F1VG1x8Bhx/MBaMtV7lontIAnpyN4XnsuC3105Q9Z0c18gGepkik/rA/uuYYnFhzCRjmWHl7eCjr97FT6ZHeKK3XKmmqbY2RtXhv+3aMlziRnDRy5nllWul0leam1y3CGBnUxxGfqzK0SOjHN4ZnJHmqmwXyW03BtbRBnxMbXNAkbnGRjOFk1XquzU1tqa2ihqTf6qhbQuD/1cQDQ0ub+AP8AjdVx3P6m83+NYNqe4lTBUDxVtnBaVJE5b3rtz3Kmxe21CmsqSrLHU5HNT2TZ7108ZU6urp896m0tSBO3fON1aOsOOaoqmv8AhuJ2e13Kbz4+27rKL3qdtto3NjOZnDAC1fX1k1dUumqHl73Hv7kral9TMZHknwUgDK5W6qRCG5dxf/FHDJ3U0hSypahXi9KhKD1eO+XKZUMhwwoJdM3JefNTHAhKEfoj5lVDo8oKvSlqde9TWm1sBJrKqKDbuDnAE/VfR+NjY42sYA1rRgAdwXE32ZrL/qfS1b5XN4o6CKWrd7N4W/1PafZdtoCIiAiIgIiIMc6RaE3HQ18pmjL3Ukjmjxc0cQ+oC43ZMWnmu6ZGNkjcx4Ba4EEHvC4h1XbXWTUdyt7s/wC2nfGM94B2PuMLKPONsrRxEhw5OB3ClmR0f64cTPvtH5hUMc3cqiKfHeudi5XlRE2RnEzBB5YVnqIi0q+Oia4F0TuredzjcH1CoqjLf17MD743H/pbLhYspcWnmpkdSWkAq6UjqKKOobWUYqWStwx7ZC10R33HcfQryp08JI2TWesirQ9xb1PyStOCd2nyB5fVd+O3KxStqQW7lY/XVJnmec9nOAp1ZI+Bj2vDmvHZIIwQVbf2VXfekmPQCTspwbgKCMYbnvKjBXNQRzUojvU5OHKwUxG6gKqXMUlzcIJalynsFTHYCpp3jGAOaCopncMbVVxyAjBVC3ZoUTXEFB1T9kSzNbTX29ubu8spI3enaf8AmxdGLWX2crSbT0S2cyN4ZazjrH/93dn+kNWzUBERAREQEREBcy/aTsL6LVcF3ijPw9fEA9wG3WM2I/l4fqumlQXu0UN8ts1BdKdlRSyjDmPH1B7iPEIOEC7C9bLg81sjpV6K7hpGSSut4krbKTkSgZfD5PA7v3uXotWuJCkXCOo5bqcJsnnsrQHlTGzYWWN1V1FPG7LoyY3H7vI+yt7uvgeHjOW7h8Z3Htz/AAVSZvNS3Pysnpqw3iTrCHmQve5xLiTuT5qmawFva2H5qt1CQXwHAz4qkcukS8JXnFujioCUE0O2UbHb81SPfg7KAue48/ZBdYmtmkDGkZOyr7nZJKSBkjC2drgO209lp8FZKbskcTvYK8074pGhpP4lBjtQHNcQSCR4clQvJfMASs1l09PXENt0T55nfLHGOJzj4ADmrff9E3/TclK6/W2eidVs6yBkoHE8ZxyG4Oe44PLZBZCqi3UktfX01HTjimqJWxRjxc4gAfiVs3Q3QXrDVJjnnpRZ6B2/X1wLXEfux/MffA810Z0edBeldIT09dKyW63aFwkZU1OzY3g5BZGNhg7jOSPFBsuzUEVqtFDb6cYhpIGQMH7rWho/JViIgIiICIiAiIgIiIIZGNkjcyRocxww5pGQR4LRfSd0Gw1xluOjgynqTlz6BxxG8/uH9k+R29FvZEHz8ulvrLVXS0dyppaWqiOHxStLXBUnFhd1az0XY9YUXw97o2yPaMRzs7MsX8Lv7HI8lzV0hdCd+031tVZw672xu+Ym/pox+8zv9W59Aswar4s9684yOaluy0kEEEbEFQOcUwUV5OepPgVBKwtXt03iB8Cq2WnMlNHI0bOaCtgtTualuU+VpbzUg89uaCS7md0ytk6H6F9Y6tLJo6A22gdv8VXZjBHi1uOJ3rjHmug9D/Z40rYurnvZkvla3f8ATjggB8owd/8AsSPJByrpLR+otW1PU6ftVTWYOHSNbwxs/iecNHuV0Bof7NhYI6jWN1JPM0lCcD0dIR9APddHUdLT0VNHT0cEVPTxjDIomBrWjwAGwU5BY9M6TsemKYQ2O209IMYL2ty938Tzlx9yrvNTwzOidNFHI6J3EwuaCWHlkeB3U1EBERAREQEREBERAREQEREBERAREQa+6QOijTmsRJPLB8Dc3bispgA4n99vJ3vv5rm3XnRFqfShkmNMbjbm7/FUoLgB4ubzb+Xmu00QfN+sZxQvGPNZBpAwVluEE7gHNcWjK7C1n0T6T1WZJau3ilrH86mjIjeT4kY4Xe4JWEab+zhYrZXPmuF3r66Di4mQtaIRjwcRkn2wg58vejrgXRut9LNViQ4Y2BheSfDAWedGHQNqarvVsumoIobZb4J453QTnimla1wdw8I+XOMdogjwXVNjsVssVI2ntNFFTRAY7I7R9XHc+5VzQeL1EQEREBERAREQEREBERAREQf/2Q==' />");
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

        StringBuilder sb = new StringBuilder("{\"prints\":[");
        boolean status = false;
        for (PrintService print : prints) {

            sb
                    .append("{\"nome\":\"")
                    .append(print.getName())
                    .append("\",\"atributes\":{");

            for (Attribute attribute : print.getAttributes().toArray()) {
                sb
                        .append("\"")
                        .append(attribute.getName())
                        .append("\":\"")
                        .append(attribute)
                        .append("\",");
                if (attribute.getName().equalsIgnoreCase("printer-is-accepting-jobs") && attribute.toString().equalsIgnoreCase("accepting-jobs")) {
                    status = true;
                }
            }
            sb
                    .deleteCharAt(sb.toString().length() - 1)
                    .append("},\"status\":")
                    .append(status)
                    .append(",\"termica\":")
                    .append(isTermica(print))
                    .append("},");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]}");
        return sb.toString();
    }

}

abstract class TermicPrinter {

    /*
     * decimal ascii values for epson ESC/P commands
     */
    private static final char ESC = 27; //escape
    private static final char AT = 64; //@
    private static final char LINE_FEED = 10; //line feed/new line
    private static final char PARENTHESIS_LEFT = 40;
    private static final char BACKSLASH = 92;
    private static final char CR = 13; //carriage return
    private static final char TAB = 9; //horizontal tab
    private static final char FF = 12; //form feed
    private static final char P = 80; //10cpi pitch
    private static final char M = 77; //12cpi pitch
    private static final char g = 103; //15cpi pitch
    private static final char p = 112; //used for choosing proportional mode or fixed-pitch
    private static final char t = 116; //used for character set assignment/selection
    private static final char l = 108; //used for setting left margin
    private static final char x = 120; //used for setting draft or letter quality (LQ) printing
    private static final char E = 69; //bold font on
    private static final char F = 70; //bold font off
    private static final char J = 74; //used for advancing paper vertically
    private static final char Q = 81; //used for setting right margin
    private static final char $ = 36; //used for absolute horizontal positioning
    public static final char ITALIC_ON = 52; //set font italic
    public static final char ITALIC_OFF = 53; //unset font italic
    public static final char CONDENSED_ON = 15;
    public static final char CONDENSED_OFF = 18;

    ByteArrayOutputStream printer = new ByteArrayOutputStream();

    public TermicPrinter printChar(char opt) {
        printer.write(opt);
        return this;
    }

    /**
     *
     * reusable init esc code
     *
     */
    public TermicPrinter escInit() throws IOException {
        printer.write(0x1B);
        printer.write("@".getBytes());
        return this;
    }

    /**
     * resets all printer settings to default
     *
     */
    public TermicPrinter resetToDefault() throws IOException {
        setInverse(false);
        setBold(false);
        setUnderline(0);
        setJustification(0);
        return this;
    }

    /**
     *
     * @param txt String to print
     */
    public TermicPrinter printString(String str) throws IOException {
        printer.write(str.getBytes());
        printer.write(0xA);
        return this;
    }

    public TermicPrinter printLine(String line) throws IOException {
        this.printString(line + "\n");
        return this;
    }

//    public TermicPrinter storeString(String str) throws IOException {
//        printer.write(str.getBytes());
//        return this;
//    }
//
//    public TermicPrinter storeChar(int hex) {
//        printer.write(hex);
//        return this;
//    }
//
//    public TermicPrinter printStorage() {
//        printer.write(0xA);
//        return this;
//    }
    /**
     * Prints n lines of blank paper.
     *
     */
    public TermicPrinter feed(int feed) throws IOException {
        //escInit();
        printer.write(0x1B);
        printer.write("d".getBytes());
        printer.write(feed);
        printer.write(LINE_FEED);
        return this;
    }

    /**
     * Prints a string and outputs n lines of blank paper.
     *
     */
    public TermicPrinter printAndFeed(String str, int feed) throws IOException {
        //escInit();
        printer.write(str.getBytes());
        //output extra paper
        printer.write(0x1B);
        printer.write("d".getBytes());
        printer.write(feed);
        return this;
    }

    /**
     * Sets bold
     *
     */
    public TermicPrinter setBold(Boolean bool) throws IOException {
        printer.write(0x1B);
        printer.write("E".getBytes());
        printer.write((int) (bool ? 1 : 0));
        printer.write((int) (bool ? CONDENSED_ON : CONDENSED_OFF));
        printer.write((int) (bool ? E : F));
        return this;
    }

    /**
     * Sets italic
     *
     */
    public TermicPrinter setItalic(Boolean bool) throws IOException {
        printer.write((int) (bool ? ITALIC_ON : ITALIC_OFF));
        return this;
    }

    /**
     * Sets white on black printing
     *
     */
    public TermicPrinter setInverse(Boolean bool) throws IOException {
        printer.write(0x1D);
        printer.write("B".getBytes());
        printer.write((int) (bool ? 1 : 0));
        return this;
    }

    /**
     * Sets underline and weight
     *
     * @param val 0 = no underline. 1 = single weight underline. 2 = double
     * weight underline.
     *
     */
    public TermicPrinter setUnderline(int val) throws IOException {
        printer.write(0x1B);
        printer.write("-".getBytes());
        printer.write(val);
        return this;
    }

    /**
     * Sets left, center, right justification
     *
     * @param val 0 = left justify. 1 = center justify. 2 = right justify.
     *
     */
    public TermicPrinter setJustification(int val) throws IOException {
        printer.write(0x1B);
        printer.write("a".getBytes());
        printer.write(val);
        return this;
    }

    /**
     * Encode and print QR code
     *
     * @param str String to be encoded in QR.
     * @param errCorrection The degree of error correction. (48 <= n <= 51) 48 =
     * level L / 7% recovery capacity. 49 = level M / 15% recovery capacity. 50
     * = level Q / 25% recovery capacity. 51 = level H / 30% recovery capacity.
     *
     * @param moduleSize The size of the QR module (pixel) in dots. The QR code
     * will not print if it is too big. Try setting this low and experiment in
     * making it larger.
     */
    public TermicPrinter printQR(String str, int errCorrect, int moduleSize) throws IOException {
        //save data function 80
        printer.write(0x1D);//init
        printer.write("(k".getBytes());//adjust height of barcode
        printer.write(str.length() + 3); //pl
        printer.write(0); //ph
        printer.write(49); //cn
        printer.write(80); //fn
        printer.write(48); //
        printer.write(str.getBytes());

        //error correction function 69
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3); //pl
        printer.write(0); //ph
        printer.write(49); //cn
        printer.write(69); //fn
        printer.write(errCorrect); //48<= n <= 51

        //size function 67
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3);
        printer.write(0);
        printer.write(49);
        printer.write(67);
        printer.write(moduleSize);//1<= n <= 16

        //print function 81
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3); //pl
        printer.write(0); //ph
        printer.write(49); //cn
        printer.write(81); //fn
        printer.write(48); //m  
        return this;
    }

    /**
     * Encode and print barcode
     *
     * @param code String to be encoded in the barcode. Different barcodes have
     * different requirements on the length of data that can be encoded.
     * @param type Specify the type of barcode 65 = UPC-A. 66 = UPC-E. 67 =
     * JAN13(EAN). 68 = JAN8(EAN). 69 = CODE39. 70 = ITF. 71 = CODABAR. 72 =
     * CODE93. 73 = CODE128.
     *
     * @param h height of the barcode in points (1 <= n <= 255) @ param w width
     * of module (2 <= n <=6). Barcode will not print if this value is too
     * large. @param font Set font of HRI characters 0 = font A 1 = font B
     * @param pos set position of HRI characters 0 = not printed. 1 = Above
     * barcode. 2 = Below barcode. 3 = Both abo
     * ve and below barcode.
     */
    public TermicPrinter printBarcode(String code, int type, int h, int w, int font, int pos) throws IOException {

        //need to test for errors in length of code
        //also control for input type=0-6
        //GS H = HRI position
        printer.write(0x1D);
        printer.write("H".getBytes());
        printer.write(pos); //0=no print, 1=above, 2=below, 3=above & below

        //GS f = set barcode characters
        printer.write(0x1D);
        printer.write("f".getBytes());
        printer.write(font);

        //GS h = sets barcode height
        printer.write(0x1D);
        printer.write("h".getBytes());
        printer.write(h);

        //GS w = sets barcode width
        printer.write(0x1D);
        printer.write("w".getBytes());
        printer.write(w);//module = 1-6

        //GS k
        printer.write(0x1D); //GS
        printer.write("k".getBytes()); //k
        printer.write(type);//m = barcode type 0-6
        printer.write(code.length()); //length of encoded string
        printer.write(code.getBytes());//d1-dk
        printer.write(0);//print barcode
        return this;
    }

    /**
     * Encode and print PDF 417 barcode
     *
     * @param code String to be encoded in the barcode. Different barcodes have
     * different requirements on the length of data that can be encoded.
     * @param type Specify the type of barcode 0 - Standard PDF417 1 - Standard
     * PDF417
     *
     * @param h Height of the vertical module in dots 2 <= n <= 8. @ param w
     * Height of the horizontal module in dots 1 <= n <= 4. @ param cols Number
     * of columns 0 <= n <= 30. @ param rows Number of rows 0 (automatic), 3 <=
     * n <= 90. @ param error set error correction level 48 <= n <= 56 (0 - 8).
     *
     */
    public TermicPrinter printPSDCode(String code, int type, int h, int w, int cols, int rows, int error) throws IOException {

        //print function 82
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(code.length()); //pl Code length
        printer.write(0); //ph
        printer.write(48); //cn
        printer.write(80); //fn
        printer.write(48); //m
        printer.write(code.getBytes()); //data to be encoded

        //function 65 specifies the number of columns
        printer.write(0x1D);//init
        printer.write("(k".getBytes());//adjust height of barcode
        printer.write(3); //pl
        printer.write(0); //pH
        printer.write(48); //cn
        printer.write(65); //fn
        printer.write(cols);

        //function 66 number of rows
        printer.write(0x1D);//init
        printer.write("(k".getBytes());//adjust height of barcode
        printer.write(3); //pl
        printer.write(0); //pH
        printer.write(48); //cn
        printer.write(66); //fn 
        printer.write(rows); //num rows

        //module width function 67
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3);//pL
        printer.write(0);//pH
        printer.write(48);//cn
        printer.write(67);//fn
        printer.write(w);//size of module 1<= n <= 4

        //module height fx 68
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3);//pL
        printer.write(0);//pH
        printer.write(48);//cn
        printer.write(68);//fn
        printer.write(h);//size of module 2 <= n <= 8

        //error correction function 69
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(4);//pL
        printer.write(0);//pH
        printer.write(48);//cn
        printer.write(69);//fn
        printer.write(48);//m
        printer.write(error);//error correction

        //choose pdf417 type function 70
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3);//pL
        printer.write(0);//pH
        printer.write(48);//cn
        printer.write(70);//fn
        printer.write(type);//set mode of pdf 0 or 1

        //print function 81
        printer.write(0x1D);
        printer.write("(k".getBytes());
        printer.write(3); //pl
        printer.write(0); //ph
        printer.write(48); //cn
        printer.write(81); //fn
        printer.write(48); //m
        return this;

    }

    /**
     * Store custom character input array of column bytes
     *
     * @param columnArray Array of bytes (0-255). Ideally not longer than 24
     * bytes.
     *
     * @param mode 0 - 8-dot single-density. 1 - 8-dot double-density. 32 -
     * 24-dot single density. 33 - 24-dot double density.
     */
    public TermicPrinter storeCustomChar(int[] columnArray, int mode) throws IOException {

        //function GS*
        printer.write(0x1B);
        printer.write("*".getBytes());
        printer.write(mode);
        printer.write((mode == 0 || mode == 1) ? columnArray.length : columnArray.length / 3);//number of cols
        printer.write(0);
        for (int i = 0; i < columnArray.length; i++) {
            printer.write(columnArray[i]);
        }
        return this;

    }

    /**
     * Store custom character input array of column bytes.	NOT WORKING
     *
     * @param spacing Integer representing Vertical motion of unit in inches.
     * 0-255
     *
     */
    public TermicPrinter setLineSpacing(int spacing) throws IOException {

        //function ESC 3
        printer.write(0x1B);
        printer.write("3".getBytes());
        printer.write(spacing);
        return this;
    }

    public TermicPrinter cut() throws IOException {
        printer.write(0x1D);
        printer.write("V".getBytes());
        printer.write(48);
        printer.write(0);
        printer.write(27);
        printer.write(109);
        return this;
    }

    public TermicPrinter feedAndCut(int feed) throws IOException {
        feed(feed);
        cut();
        return this;
    }

    public TermicPrinter beep() throws IOException {
        printer.write(0x1B);
        printer.write("(A".getBytes());
        printer.write(4);
        printer.write(0);
        printer.write(48);
        printer.write(55);
        printer.write(3);
        printer.write(15);
        return this;
    }

    public byte[] getBytes() {
        return printer.toByteArray();
    }

    public byte[] getDemo() throws IOException {
        //print samples of all functions here
        return this.resetToDefault().
                escInit().
                printChar((char) 178).
                printChar((char) 177).
                printChar((char) 176).
                printLine("Hello World").
                printLine("printString().").
                setBold(true).
                printLine("setBold(true)").
                setBold(false).
                setUnderline(1).
                printLine("setUnderline(1)").
                setUnderline(2).
                printLine("setUnderline(2)").
                setUnderline(0).
                setInverse(true).
                printLine("setInverse(true)").
                setInverse(false).
                setJustification(0).
                printLine("setJustification(0)\n//left - default").
                setJustification(1).
                printLine("setJustification(1)\n//center").
                setJustification(2).
                printLine("setJustification(2)\n//right").
                setJustification(1).
                printQR("http://mikeias.net", 51, 8).
                feed(3).
                printAndFeed("\nMIKEIAS.NET", 4).
                resetToDefault()
                .feed(3)
                .setJustification(1)
                .printLine("******NAO E DOCUMENTO FISCAL******")
                .setJustification(0)
                .setBold(true)
                .printLine("IMPERIO DOS ESPETOS")
                .setBold(false)
                .printLine("MOV.: 24/11/17 - IMP.: 25/11/17 - 00:34H")
                .printLine("OPER.: MIRELLE - GARCON: MIRELLE GA")
                .setBold(true)
                .printLine("MESA: 10 SEQ: 36")
                .setBold(false)
                .printLine("------------------------------------------")
                .printLine("------------------------------------------")
                .printLine("QTDE.   DESCRICAO      UNIT.         VALOR")
                .printLine("------------------------------------------")
                .printLine("2       CORACAO DE BOI  4,37          8,74")
                .printLine("2       CARNE           4,37          8,74")
                .printLine("1       AGUA MINERAL    2,50          2,50")
                .printLine("1       VINAGRETE       2,75          2,75")
                .printLine("1       FANTA LARANJA   4,00          4,00")
                .feed(1)
                .setJustification(2)
                .printLine("SUBTOTAL   :             26,73")
                .printLine("SERVICO   :       2,67")
                .setJustification(0)
                .setBold(true)
                .printLine("TOTAL:                    29,40")
                .setBold(false)
                .printLine("------------------------------------------")
                .printLine("TEMPO  DE PERMANENCIA: 00:38:24")
                .setBold(true)
                .printLine("NO. PESSOA(S): 1")
                .printLine("POR PESS.: 29,40")
                .setBold(false)
                .feed(1)
                .setJustification(1)
                .printLine("BEM VINDO AO IMPERIO")
                .feed(1)
                .setJustification(2)
                .printLine("SISTEMA E-RESTAURANTE")
                .setJustification(1)
                .printLine("******NAO E DOCUMENTO FISCAL******")
                .feedAndCut(2)
                .getBytes();
    }

    abstract void imprime() throws Exception;

    abstract void selecionaImpressora(String impressoraSelecionada) throws Exception;

    void print(String text) throws Exception {
        resetToDefault()
                .escInit()
                .printLine(text);
        imprime();
    }

}

class TermicPrinterDefault extends TermicPrinter {

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
    void imprime() throws Exception {
        DocPrintJob dpj = impressora.createPrintJob();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(getBytes(), flavor, null);
        dpj.print(doc, null);
    }

}

class TermicPrinterSerial extends TermicPrinter {

    SerialPort port;

    @Override
    void imprime() throws Exception {
        if (port != null) {
            port.openPort();
            port.getOutputStream().write(getBytes());
            port.closePort();
        }
        System.err.println(new String(getBytes()));
        throw new Exception("Impressora não selecionada!");
    }

    @Override
    void selecionaImpressora(String impressoraSelecionada) throws Exception {
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

enum Baud {

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

enum DataBits {

    DATABITS_5(5),
    DATABITS_6(6),
    DATABITS_7(7),
    DATABITS_8(8);

    public final int val;

    DataBits(int val) {
        this.val = val;
    }

}

enum FlowControl {

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

enum Parity {

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

enum StopBits {

    STOPBITS_1(SerialPort.ONE_STOP_BIT),
    STOPBITS_2(SerialPort.TWO_STOP_BITS),
    STOPBITS_1_5(SerialPort.ONE_POINT_FIVE_STOP_BITS);

    public final int val;

    StopBits(int val) {
        this.val = val;
    }

}

class SerialConfig {

    /**
     * Convenience method for 9600/8-N-1 configuration. That is - 8 data bits,
     * no parity bit, 1 stop bit, at 9600 baud.
     *
     * @return 9600/8-N-1 configuration
     */
    public static SerialConfig CONFIG_9600_8N1() {
        return new SerialConfig(
                Baud.BAUD_9600,
                DataBits.DATABITS_8, Parity.PARITY_NONE, StopBits.STOPBITS_1, FlowControl.FLOWCONTROL_NONE
        );
    }

    /**
     * Convenience method for general 8-N-1 configuration. That is - 8 data
     * bits, no parity bit, 1 stop bit.
     *
     * @param baud bits-per-second
     * @return baud/8-N-1 configuration
     */
    public static SerialConfig CONFIG_8N1(Baud baud) {
        return new SerialConfig(
                baud,
                DataBits.DATABITS_8, Parity.PARITY_NONE, StopBits.STOPBITS_1, FlowControl.FLOWCONTROL_NONE
        );
    }

    private final Baud baud;
    private final DataBits dataBits;
    private final StopBits stopBits;
    private final FlowControl flowControl;
    private final Parity parity;

    public SerialConfig(Baud baud, DataBits dataBits, Parity parity, StopBits stopBits, FlowControl flowControl) {

        if (baud == null || dataBits == null || parity == null || stopBits == null || flowControl == null) {
            throw new NullPointerException(SerialConfig.class.getSimpleName() + " does not accept null parameters.");
        }

        this.baud = baud;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.flowControl = flowControl;
        this.parity = parity;
    }

    public Baud getBaud() {
        return baud;
    }

    public DataBits getDataBits() {
        return dataBits;
    }

    public StopBits getStopBits() {
        return stopBits;
    }

    public FlowControl getFlowControl() {
        return flowControl;
    }

    public Parity getParity() {
        return parity;
    }

}

enum SerialFactory {

    ; // Static Factory

       
        
    static SerialPort jSerialPort(String portName, SerialConfig config) {

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
