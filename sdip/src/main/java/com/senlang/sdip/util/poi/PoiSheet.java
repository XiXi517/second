/**
 *
 */
package com.senlang.sdip.util.poi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.senlang.sdip.util.FormatException;

/**
 * @author Mc.D
 */
public class PoiSheet {
    Sheet sheet;

    private PoiSheet(Sheet sheet) throws Exception {
        this.sheet = sheet;
    }

    public static PoiSheet of(Sheet sheet) throws Exception {
        return new PoiSheet(sheet);
    }

    public PoiSheet setCellValue(String position, String value) throws FormatException {
        Pattern p = Pattern.compile("[a-zA-Z]{1,2}[1-9]+[0-9]*");
        if (!p.matcher(position).matches()) {
            throw new FormatException();
        }

        Matcher m = Pattern.compile("[a-zA-Z]+").matcher(position);
        m.find();
        String columnStr = m.group().toUpperCase();

        m = Pattern.compile("[0-9]+").matcher(position);
        m.find();
        String rowStr = m.group();

        int columnNo = Position.get(columnStr);
        int rowNo = Integer.parseInt(rowStr) - 1;

        Row row = this.sheet.getRow(rowNo);
        if (row == null) {
            row = this.sheet.createRow(rowNo);
        }
        Cell cell = row.getCell(columnNo);
        if (cell == null) {
            cell = row.createCell(columnNo);
        }

        cell.setCellValue(value);
        return this;
    }

    public PoiSheet autoWidthColumn(int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            this.sheet.autoSizeColumn(i, true);
        }
        return this;
    }

    public static void autoWidthColumn(Sheet sheet, int columnCount, boolean useMergedCells) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i, useMergedCells);
        }
    }
}
