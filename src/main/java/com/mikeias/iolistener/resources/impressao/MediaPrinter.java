/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao;

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

/**
 *
 * @author mfernandes
 */
public class MediaPrinter {

    public static MediaSizeName getMediaByName(String media) {

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

    public static OrientationRequested getOrientationByName(String orientation) {
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
