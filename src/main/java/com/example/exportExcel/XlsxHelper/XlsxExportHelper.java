package com.example.exportExcel.XlsxHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class XlsxExportHelper {

	private XSSFWorkbook workbook = new XSSFWorkbook();;
    private XSSFSheet sheet;
    private List<? extends AbstractExportXlsx> data;
    
    public XSSFWorkbook write(final List<? extends AbstractExportXlsx> data, final String sheetName) throws IOException {
    	this.data = data;
    	sheet = workbook.createSheet(sheetName);
    	this.writeHeaderLine();
    	this.writeDataLines();
    	return this.workbook;
    }
     
    private void writeHeaderLine() {
        Row row = sheet.createRow(0);
        CellStyle style = this.createCellStyle(true, 16);
        
        AtomicInteger index = new AtomicInteger();
        List<String> titles = this.data.get(0).generateXlsxTitles();
        titles.forEach(title -> createCell(row, index.getAndIncrement(), title, style) );
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellStyle(style);
        
        if (value instanceof Number) {
            cell.setCellValue(String.valueOf(value));
            return;
        }

    	if (value instanceof LocalDate) {
        	cell.setCellValue(value.toString());
        	return;
        } 

    	cell.setCellValue((String) value);
    }
    
    private CellStyle createCellStyle(final Boolean isBold, final int fontHeight) {
        XSSFFont font = workbook.createFont();
        font.setBold(isBold);
        font.setFontHeight(fontHeight);
        
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        
        return style;
    }
     
    private void writeDataLines() throws IOException {
        int rowCount = 1;
 
        CellStyle style = this.createCellStyle(false, 14);
                 
        for (AbstractExportXlsx register : data) {
            Row row = sheet.createRow(rowCount++);
             
            AtomicInteger columnCount = new AtomicInteger();
            
            List<String> values = register.generateXlsxValues();
            values.forEach(value -> createCell(row, columnCount.getAndIncrement(), value, style) );
        }
    }
    
}
