/**
 *
 */
package com.senlang.sdip.util.poi;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.senlang.sdip.util.DateTime;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import com.senlang.sdip.util.FormatException;

/**
 * @author Mc.D
 */
public final class Chain {
    Sheet sheet;
    int startColumnNo;
    int startRowNo;
    int columnNo;
    int rowNo;
    CellStyle style;
    //Row row;

    private Chain(Sheet sheet, String position) throws Exception {
        Pattern p = Pattern.compile("[a-zA-Z]{1,2}[1-9]+[0-9]*");
        if (!p.matcher(position).matches()) {
            throw new FormatException();
        }

        Matcher m = Pattern.compile("[a-zA-Z]+").matcher(position);
        m.find();
        String column = m.group().toUpperCase();

        m = Pattern.compile("[0-9]+").matcher(position);
        m.find();
        String row = m.group();

        this.columnNo = Position.get(column);
        this.startColumnNo = this.columnNo;
        this.rowNo = Integer.parseInt(row) - 1;
        this.startRowNo = this.rowNo;
        this.sheet = sheet;
    }

    public static Chain of(Sheet sheet) throws Exception {
        return new Chain(sheet, "A1");
    }

    public static Chain of(Workbook wb) throws Exception {
        return new Chain(wb.createSheet(), "A1");
    }

    public static Chain of(Sheet sheet, String position) throws Exception {
        return new Chain(sheet, position);
    }

    public static Chain of(Workbook wb, String position) throws Exception {
        return new Chain(wb.createSheet(), position);
    }

    public Chain style(CellStyle style) {
        this.style = style;
        return this;
    }

    public Chain style(PoiStyle style) {
        this.style = style.toStyle();
        return this;
    }

    public Chain output(double value) {
        return output(value, 1);
    }

    public Chain output(double value, int col) {
        return output(value, 1, col);
    }

    public Chain output(double value, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j);
        }
        this.getCell().setCellValue(value);

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }

    public Chain output(long value) {
        return output(value, 1);
    }

    public Chain output(long value, int col) {
        return output(value, 1, col);
    }

    public Chain output(long value, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j);
        }
        this.getCell().setCellValue(value);

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }

    public Chain output(boolean value) {
        return output(value, 1);
    }

    public Chain output(boolean value, int col) {
        return output(value, 1, col);
    }

    public Chain output(boolean value, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j);
        }
        this.getCell().setCellValue(value ? "是" : "否");

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }

    public Chain output(BigDecimal value) {
        return output(value, 1);
    }

    public Chain output(BigDecimal value, int col) {
        return output(value, 1, col, true);
    }

    public Chain output(BigDecimal value, int row, int col, boolean currency) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j);
        }
        BigDecimal v;
        if (currency) {
            v = new BigDecimal(value.toString());
            v.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            v = value;
        }
        this.getCell(this.rowNo, this.columnNo).setCellValue(v.toString());

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }

    public Chain output(String value) {
        return output(value, 1);
    }

    public Chain output(String value, int col) {
        return output(value, 1, col);
    }

    public Chain output(String value, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j);
        }
        this.getCell().setCellValue(value);

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }

    public Chain formula(String formula) {
        return formula(formula, 1);
    }

    public Chain formula(String formula, int col) {
        return formula(formula, 1, col);
    }

    public Chain formula(String formula, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                this.getCell(this.rowNo + i, this.columnNo + j).setCellType(CellType.FORMULA);
        }
        this.getCell().setCellFormula(formula);

        if (col > 1 || row > 1) {
            this.sheet.addMergedRegion(
                    new CellRangeAddress(this.rowNo, this.rowNo + row - 1, this.columnNo, this.columnNo + col - 1));
        }
        this.columnNo += col;
        return this;
    }


    public Chain empty() {
        return empty(1);
    }

    public Chain empty(int col) {
        for (int i = this.columnNo; i < this.columnNo + col; i++) {
            this.getCell(i);
        }
//        if (col > 1) {
//            this.sheet.addMergedRegion(
//                    new CellRangeAddress(this.rowNo, this.rowNo, this.columnNo, this.columnNo + col - 1));
//        }
        this.columnNo += col;
        return this;
    }

    public Chain skip() {
        return empty(1);
    }

    public Chain skip(int col) {
        this.columnNo += col;
        return this;
    }

    public Chain nextRow() {
        this.columnNo = this.startColumnNo;
        this.rowNo++;
        return this;
    }

    public int getRowNo() {
        return this.rowNo;
    }

    public int getColumnNo() {
        return this.columnNo;
    }

    private Cell getCell() {
        return getCell(this.rowNo, this.columnNo);
    }

    private Cell getCell(int columnNo) {
        return getCell(this.rowNo, columnNo);
    }

    private Cell getCell(int rowNo, int columnNo) {
        Row row = this.sheet.getRow(rowNo);
        if (row == null) {
            row = this.sheet.createRow(rowNo);
        }
        Cell cell = row.getCell(columnNo);
        if (cell == null) {
            cell = row.createCell(columnNo);
        }
        if (this.style == null) {
            this.style = PoiStyle.newStyle(this.sheet.getWorkbook()).font(this.sheet.getWorkbook()).toStyle();
        }
        cell.setCellStyle(this.style);
        return cell;
    }
}
