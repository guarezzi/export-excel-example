package com.example.exportExcel.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.example.exportExcel.XlsxHelper.AbstractExportXlsx;
import com.example.exportExcel.XlsxHelper.XlsxExportHelper;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateExcelService {
    
    @Autowired
    XlsxExportHelper service;

    public void generate(final List<AbstractExportXlsx> dto) throws IOException {
        OutputStream os = new FileOutputStream("example.xlsx");
        XSSFWorkbook workbook = this.service.write(dto, "example");
        workbook.write(os);
    }

}
