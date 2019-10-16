/**
 *
 */
package com.senlang.sdip.util.poi;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Mc.D
 */
public final class PoiFont {
    Font font;

    private PoiFont(Font font) {
        this.font = font;
    }

    public static PoiFont of(Font font) {
        return new PoiFont(font);
    }

    public static PoiFont newFont(Workbook wb) {
        return newFont(wb, "宋体");
    }

    public static PoiFont newFont(Workbook wb, String fontName) {
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) 9);
        return new PoiFont(font);
    }

    public PoiFont size(short point) {
        this.font.setFontHeightInPoints(point);
        return this;
    }

    public PoiFont size(int point) {
        return size((short) point);
    }

    public PoiFont bold() {

        return this.bold(true);
    }

    public PoiFont bold(boolean isBold) {
        this.font.setBold(isBold);
        return this;
    }

    public PoiFont color(short color) {
        this.font.setColor(color);
        return this;
    }

    public PoiFont red() {
        return this.color(Font.COLOR_RED);
    }

    public PoiFont italic() {
        return this.italic(true);
    }

    public PoiFont italic(boolean isItalic) {
        this.font.setItalic(isItalic);
        return this;
    }

    public Font toFont() {
        return this.font;
    }
}
