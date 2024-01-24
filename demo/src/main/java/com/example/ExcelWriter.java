package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
   public static void writeResultsToExcel(String outputPath, List<String> commonItems,
         List<String> differentItemsTable1, List<String> differentItemsTable2) {
      try (Workbook workbook = new XSSFWorkbook()) {
         Sheet sheet = workbook.createSheet("Resultados");

         CellStyle greenCellStyle = createCellStyle(workbook, IndexedColors.GREEN);
         CellStyle redCellStyle = createCellStyle(workbook, IndexedColors.RED);

         int rowNumber = 0;

         Row headerRow = sheet.createRow(rowNumber++);
         headerRow.createCell(0).setCellValue("Email");
         headerRow.createCell(1).setCellValue("Status");
         headerRow.createCell(2).setCellValue("Tabela");

         for (String item : commonItems) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item);
            Cell statusCell = row.createCell(1);
            statusCell.setCellValue("Comum");
            statusCell.setCellStyle(greenCellStyle);
         }

         for (String item : differentItemsTable1) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item);
            Cell statusCell = row.createCell(1);
            statusCell.setCellValue("Diferente");
            statusCell.setCellStyle(redCellStyle);
            row.createCell(2).setCellValue("Tabela 1");
         }

         for (String item : differentItemsTable2) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item);
            Cell statusCell = row.createCell(1);
            statusCell.setCellValue("Diferente");
            statusCell.setCellStyle(redCellStyle);
            row.createCell(2).setCellValue("Tabela 2");
         }

         try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static CellStyle createCellStyle(Workbook workbook, IndexedColors color) {
      CellStyle style = workbook.createCellStyle();
      style.setFillForegroundColor(color.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      return style;
   }
}
