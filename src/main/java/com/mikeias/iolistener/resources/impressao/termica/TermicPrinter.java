/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.termica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author mfernandes
 */
public abstract class TermicPrinter {

    /*
     * decimal ascii values for epson ESC/P commands
     */
    public static final char ESC = 27; //escape
    public static final char AT = 64; //@
    public static final char LINE_FEED = 10; //line feed/new line
    public static final char PARENTHESIS_LEFT = 40;
    public static final char BACKSLASH = 92;
    public static final char CR = 13; //carriage return
    public static final char TAB = 9; //horizontal tab
    public static final char FF = 12; //form feed
    public static final char P = 80; //10cpi pitch
    public static final char M = 77; //12cpi pitch
    public static final char g = 103; //15cpi pitch
    public static final char p = 112; //used for choosing proportional mode or fixed-pitch
    public static final char t = 116; //used for character set assignment/selection
    public static final char l = 108; //used for setting left margin
    public static final char x = 120; //used for setting draft or letter quality (LQ) printing
    public static final char E = 69; //bold font on
    public static final char F = 70; //bold font off
    public static final char J = 74; //used for advancing paper vertically
    public static final char Q = 81; //used for setting right margin
    public static final char $ = 36; //used for absolute horizontal positioning
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

    public abstract void imprime() throws Exception;

    public abstract void selecionaImpressora(String impressoraSelecionada) throws Exception;

    public void print(String text) throws Exception {
        resetToDefault()
                .escInit()
                .printLine(text);
        imprime();
    }

}
