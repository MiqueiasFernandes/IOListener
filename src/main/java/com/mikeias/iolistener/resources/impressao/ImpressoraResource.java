/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao;

import com.mikeias.iolistener.resources.impressao.padrao.PrintPreview;
import com.mikeias.iolistener.resources.impressao.termica.TermicPrinter;
import com.mikeias.iolistener.resources.impressao.termica.TermicPrinterSerial;
import com.mikeias.iolistener.resources.impressao.termica.TermicPrinterDefault;
import static com.mikeias.iolistener.resources.impressao.MediaPrinter.getMediaByName;
import static com.mikeias.iolistener.resources.impressao.MediaPrinter.getOrientationByName;
import com.mikeias.iolistener.resources.impressao.padrao.JavaFX;
import groovy.json.JsonOutput;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.awt.image.BufferedImage;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
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

    static final int limit = 2 * 60;

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
                + "  <form action=\"http://localhost:9090/impressora/print/iso-a4\" method=\"post\">\n"
                + "    <p>Text: <input type=\"text\" name=\"content\"></p>\n"
                + "    <p>Pages: <input type=\"text\" name=\"pages\"></p>\n"
                + "    <p>Printer: <input type=\"text\" name=\"printer\"></p>\n"
                + "    <p>largura: <input type=\"number\" name=\"largura\" value=\"595\"> 595</p>\n"
                + "    <p>altura: <input type=\"number\" name=\"altura\" value=\"841\"> 841</p>\n"
                + "    <p>timeout : <input type=\"number\" name=\"timeout\" value=\"5000\"></p>\n"
                + "    <p>marginsx : <input type=\"number\" name=\"marginsx\" value=\"0\"></p>\n"
                + "    <p>marginsy : <input type=\"number\" name=\"marginsy\" value=\"0\"></p>\n"
                + "    <p>marginix : <input type=\"number\" name=\"marginix\" value=\"0\"></p>\n"
                + "    <p>marginiy : <input type=\"number\" name=\"marginiy\" value=\"0\"></p>\n"
                + "    <p>Tipo: <input type=\"text\" name=\"tipo\" value=\"default\"></p>\n"
                + "<p> orientação: <select name=\"orientation\">\n"
                + "  <option value=\"portrait\">portrait</option>\n"
                + "  <option value=\"landscape\">landscape</option>\n"
                + "</select> </p>\n"
                + "<button type=\"submit\">enviar</button>"
                + "  </form>\n"
                + "</body>\n"
                + "</html>";
    }

    @GET
    @Path("print/html")
    @Produces(MediaType.TEXT_HTML)
    public String printhtml() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Simple Jersey</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <form action=\"http://localhost:9090/impressora/print/html/iso-a4\" method=\"post\">\n"
                + "    <p>Text: <input type=\"text\" name=\"content\"></p>\n"
                + "    <p>Pages: <input type=\"text\" name=\"pages\"></p>\n"
                + "    <p>Printer: <input type=\"text\" name=\"printer\"></p>\n"
                + "    <p>largura: <input type=\"number\" name=\"largura\" value=\"595\"> 595</p>\n"
                + "    <p>altura: <input type=\"number\" name=\"altura\" value=\"841\"> 841</p>\n"
                + "    <p>timeout : <input type=\"number\" name=\"timeout\" value=\"5000\"></p>\n"
                + "    <p>marginsx : <input type=\"number\" name=\"marginsx\" value=\"0\"></p>\n"
                + "    <p>marginsy : <input type=\"number\" name=\"marginsy\" value=\"0\"></p>\n"
                + "    <p>marginix : <input type=\"number\" name=\"marginix\" value=\"0\"></p>\n"
                + "    <p>marginiy : <input type=\"number\" name=\"marginiy\" value=\"0\"></p>\n"
                + "    <p>Tipo: <input type=\"text\" name=\"tipo\" value=\"default\"></p>\n"
                + "<p> orientação: <select name=\"orientation\">\n"
                + "  <option value=\"portrait\">portrait</option>\n"
                + "  <option value=\"landscape\">landscape</option>\n"
                + "</select> </p>\n"
                + "<button type=\"submit\">enviar</button>"
                + "  </form>\n"
                + "</body>\n"
                + "</html>";
    }
    
    
    
    @GET
    @Path("text")
    @Produces(MediaType.TEXT_HTML)
    public String testText() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Impressão em MODO TEXTO</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Impressão em MODO TEXTO</h1><br>"
                + "  <form action=\"http://localhost:9090/impressora/print/text/iso-a4\" method=\"post\">\n"
                + "    <p>Text: <input type=\"text\" name=\"content\"></p>\n"
                + "    <p>Pages: <input type=\"text\" name=\"pages\" value=\"all\"></p>\n"
                + "    <p>Printer: <input type=\"text\" name=\"printer\"></p>\n"
                + "<p> orientação: <select name=\"orientation\">\n"
                + "  <option value=\"portrait\">portrait</option>\n"
                + "  <option value=\"landscape\">landscape</option>\n"
                + "</select> </p>\n"
                + "<button type=\"submit\">enviar</button>"
                + "  </form>\n"
                + "</body>\n"
                + "</html>";
    }
    
    @POST
    @Path("print/text/{media}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postText(
            @PathParam("media") String media,
            @FormParam("orientation") String orientation,
            @FormParam("pages") String pages,
            @FormParam("printer") String printer,
            @FormParam("content") String content) {
        
        System.out.println("impressao parametros: "
                + "media: " + media
                + ", orientation: " + orientation
                + ", pages: " + pages
                + ", printer: " + printer
                + ", content: " + content
        );
        
         try {
                    HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(getMediaByName(media));
                    set.add(getOrientationByName(orientation));
                    PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);
                    JTextPane mTextPane = new JTextPane();
                    mTextPane.setContentType("text/html");
                    mTextPane.setText(content);
                    PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
                    preview.selecionaImpressora(printer);
                    return preview.printPages(pages, false);
        } catch (Exception ex) {
            return "{\"error\":\"" + ex.toString() + "\"}";
        }
    }
    

    @POST
    @Path("print/{media}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMethod(
            @PathParam("media") String media,
            @FormParam("orientation") String orientation,
            @FormParam("pages") String pages,
            @FormParam("dialog") String dialog,
            @FormParam("printer") String printer,
            @FormParam("largura") int largura,
            @FormParam("altura") int altura,
            @FormParam("tipo") String tipo,
            @FormParam("marginsx") int marginsx,
            @FormParam("marginsy") int marginsy,
            @FormParam("marginix") int marginix,
            @FormParam("marginiy") int marginiy,
            @FormParam("timeout") int timeout,
            @FormParam("content") String content) {

        System.out.println("impressao parametros: "
                + "media: " + media
                + ", orientation: " + orientation
                + ", pages: " + pages
                + ", dialog: " + dialog
                + ", printer: " + printer
                + ", largura: " + largura
                + ", altura: " + altura
                + ", tipo: " + tipo
                + ", marginsx: " + marginsx
                + ", marginsy: " + marginsy
                + ", marginix: " + marginix
                + ", marginiy: " + marginiy
                + ", content: " + content
                + ", timeout: " + timeout
        );

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
                    Object[] imp = impressaoPadrao(
                            timeout,
                            content,
                            largura,
                            altura,
                            media,
                            orientation,
                            marginsx,
                            marginsy,
                            marginix,
                            marginiy,
                            printer);

                    PrintPreview preview = (PrintPreview) imp[0];
                    String error = (String) imp[1];

                    if (preview == null || error != null) {
                        return error;
                    }

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

    @POST
    @Path("print/html/{media}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postMethodHTML(
            @PathParam("media") String media,
            @FormParam("orientation") String orientation,
            @FormParam("pages") String pages,
            @FormParam("dialog") String dialog,
            @FormParam("printer") String printer,
            @FormParam("largura") int largura,
            @FormParam("altura") int altura,
            @FormParam("tipo") String tipo,
            @FormParam("marginsx") int marginsx,
            @FormParam("marginsy") int marginsy,
            @FormParam("marginix") int marginix,
            @FormParam("marginiy") int marginiy,
            @FormParam("timeout") int timeout,
            @FormParam("content") String content) {

        System.out.println("impressao html parametros: "
                + "media: " + media
                + ", orientation: " + orientation
                + ", pages: " + pages
                + ", dialog: " + dialog
                + ", printer: " + printer
                + ", largura: " + largura
                + ", altura: " + altura
                + ", tipo: " + tipo
                + ", marginsx: " + marginsx
                + ", marginsy: " + marginsy
                + ", marginix: " + marginix
                + ", marginiy: " + marginiy
                + ", content: " + content
                + ", timeout: " + timeout
        );

        Object[] imp = impressaoPadrao(
                timeout,
                content,
                largura,
                altura,
                media,
                orientation,
                marginsx,
                marginsy,
                marginix,
                marginiy,
                printer);

        PrintPreview preview = (PrintPreview) imp[0];
        String error = (String) imp[1];

        if (preview == null || error != null) {
            return error;
        }

        ArrayList<String> imagesOfPages = preview.getImagesOfPages();

        StringBuilder sb = new StringBuilder("<html><body>\n");

        int k = 0;

        for (String imagesOfPage : imagesOfPages) {

            sb.append("\n<br>\n imagem ").append(k++).append("\n<br>\n");
            sb.append("<img src=\"data:image/png;base64,");
            sb.append(imagesOfPage);
            sb.append("\" />\n");

        }

        sb.append("</html></body>");

        return sb.toString();
    }

    Object[] impressaoPadrao(
            int timeout,
            String content,
            int largura,
            int altura,
            String media,
            String orientation,
            int marginsx,
            int marginsy,
            int marginix,
            int marginiy,
            String printer
    ) {
        try {
            System.out.println("*******************nova impressao**************************");

            JavaFX javaFX = new JavaFX(timeout);
            javaFX.inicializar(content, largura, altura);

            int i = 0;

            while (!javaFX.isTerminado() && i++ < limit) {
                Thread.sleep(500);
                System.out.println("aguardando imagem... " + ((limit/2) - (i / 2)));
            }

            BufferedImage image = JavaFX.getImagem();

            if (image != null) {

                HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                set.add(getMediaByName(media));
                set.add(getOrientationByName(orientation));
                PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

                Printable pb = new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

                        int pageWIDTH = (int) pageFormat.getPaper().getWidth();
                        int pageHEIGHT = (int) pageFormat.getPaper().getHeight();

                        if (pageFormat.getOrientation() == pageFormat.LANDSCAPE) {
                            pageWIDTH = pageHEIGHT;
                            pageHEIGHT = (int) pageFormat.getPaper().getWidth();
                        }

                        pageWIDTH -= (marginix + marginsx);
                        pageHEIGHT -= (marginiy + marginsy);

                        int imageWIDTH = image.getWidth();
                        int imagetHEIGHT = image.getHeight();

                        Object[] dt = getBounds(pageWIDTH, pageHEIGHT, imageWIDTH, imagetHEIGHT, pageIndex);

                        Rectangle rimage = (Rectangle) dt[0];
                        Rectangle rpage = (Rectangle) dt[1];
                        int paginas = (int) dt[2];

                        if (pageIndex >= paginas) {
                            return 1;
                        }

                        BufferedImage subimage = image;

                        if (paginas > 1) {
                            subimage = image.getSubimage(
                                    rimage.x,
                                    rimage.y,
                                    rimage.width,
                                    rimage.height);
                        }

                        graphics.drawImage(
                                subimage,
                                marginsx, marginsy,
                                pageWIDTH,
                                rpage.height,
                                null);

                        return paginas > pageIndex ? Printable.PAGE_EXISTS : 1;

                    }
                };

                PrintPreview preview = new PrintPreview(pb, pf);
                preview.selecionaImpressora(printer);
                return new Object[]{preview, null};
            }
            return new Object[]{null, "imagem não encontrada"};
        } catch (Exception ex) {
            return new Object[]{null, ex};
        }
    }

    Object[] getBounds(int pageW, int pageH, int imagW, int imagH, int pageIndex) {

        Rectangle rimage = new Rectangle();
        Rectangle rpage = new Rectangle();

        rimage.setRect(0, 0, 0, 0);
        rpage.setRect(0, 0, 0, 0);

        float ppi = (0.00001f + imagW) / pageW; //// qtas X a imagem em relacao a pagina
        float ppp = (0.00001f + pageH) / pageW; //// qtas X a altura em relacao a largura

        int paginas = Integer.max((int) Math.ceil(imagH / (imagW * ppp)), 1);

        Point pageReal = new Point(imagW, (int) (imagW * ppp));

        int orY = pageReal.y * pageIndex;
        int desY = Integer.min(pageReal.y * (pageIndex + 1), imagH);
        int tamP = desY - orY;

        rimage.x = 0;
        rimage.y = orY;
        rimage.width = imagW;
        rimage.height = tamP;

        rpage.x = 0;
        rpage.y = 0;
        rpage.width = pageW;
        rpage.height = (int) (pageH * ((tamP + 0.00001f) / pageReal.y));

        return new Object[]{rimage, rpage, paginas};
    }

}
