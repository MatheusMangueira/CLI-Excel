package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

   public static void writeResultsToExcel(String outputPath, List<String> commonItems, List<String> differentItems) {
      try (Workbook workbook = new XSSFWorkbook()) {
         Sheet sheet = workbook.createSheet("Resultados");

         // Estilo para células com itens comuns (verde)
         CellStyle greenCellStyle = createCellStyle(workbook, IndexedColors.GREEN);

         // Estilo para células com itens diferentes (vermelho)
         CellStyle redCellStyle = createCellStyle(workbook, IndexedColors.RED);

         int rowNumber = 0;

         // Escrever cabeçalho
         Row headerRow = sheet.createRow(rowNumber++);
         headerRow.createCell(0).setCellValue("Email");
         headerRow.createCell(1).setCellValue("Status");

         // Escrever itens comuns (verde)
         for (String item : commonItems) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item);
            Cell statusCell = row.createCell(1);
            statusCell.setCellValue("Comum");
            statusCell.setCellStyle(greenCellStyle);
         }

         // Escrever itens diferentes (vermelho)
         for (String item : differentItems) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item);
            Cell statusCell = row.createCell(1);
            statusCell.setCellValue("Diferente");
            statusCell.setCellStyle(redCellStyle);
         }

         // Salvar o arquivo Excel
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
