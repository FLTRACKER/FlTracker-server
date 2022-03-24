package ru.ds.fltracker.utils;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

public class ReportUtils {
    public static Font getDefaultFont() {
        return FontFactory.getFont("/fonts/Play-Regular.ttf", "cp1251", BaseFont.EMBEDDED, 14);
    }
}
