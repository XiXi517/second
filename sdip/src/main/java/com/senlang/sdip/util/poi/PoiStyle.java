/**
 * 
 */
package com.senlang.sdip.util.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Mc.D
 *
 */
public final class PoiStyle {
	CellStyle style;

	private PoiStyle(CellStyle style) {
		this.style = style;
	}

	public static PoiStyle of(CellStyle style) {
		return new PoiStyle(style);
	}

	public static PoiStyle newStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return new PoiStyle(style);
	}

	public PoiStyle border() {
		this.style.setBorderBottom(BorderStyle.THIN);
		this.style.setBorderLeft(BorderStyle.THIN);
		this.style.setBorderRight(BorderStyle.THIN);
		this.style.setBorderTop(BorderStyle.THIN);
		return this;
	}

	public PoiStyle border(BorderStyle border) {
		this.style.setBorderBottom(border);
		this.style.setBorderLeft(border);
		this.style.setBorderRight(border);
		this.style.setBorderTop(border);
		return this;
	}

	public PoiStyle left() {
		this.style.setAlignment(HorizontalAlignment.LEFT);
		return this;
	}

	public PoiStyle right() {
		this.style.setAlignment(HorizontalAlignment.RIGHT);
		return this;
	}

	public PoiStyle center() {
		this.style.setAlignment(HorizontalAlignment.CENTER);
		return this;
	}

	public PoiStyle font(Font font) {
		this.style.setFont(font);
		return this;
	}

	public PoiStyle font(PoiFont font) {
		return this.font(font.toFont());
	}

	public PoiStyle font(Workbook wb) {
		return this.font(PoiFont.newFont(wb).toFont());
	}

	public PoiStyle wrap(boolean wrapped) {
		this.style.setWrapText(wrapped);
		return this;
	}

	public PoiStyle wrap() {
		return this.wrap(true);
	}

	public CellStyle toStyle() {
		return this.style;
	}
}
