package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReading {
   public List<File> readFiles(String path) throws IOException {
      List<File> files = new ArrayList<>();

      FileInputStream file = new FileInputStream(path);

      Workbook workbook = new XSSFWorkbook(file);
      Sheet sheet = workbook.getSheetAt(0);

      List<Row> rows = (List<Row>) toList(sheet.iterator());

      List<Cell> headerCells = (List<Cell>) toList(rows.get(0).cellIterator());

      int nomeIndex = findColumnIndex(headerCells, "nome");
      int emailIndex = findEmailColumnIndex(headerCells);

      if (nomeIndex == -1 || emailIndex == -1) {
         throw new IllegalArgumentException("As colunas 'Nome' e 'Email' são necessárias no arquivo Excel.");
      }

      rows.remove(0);

      rows.forEach(row -> {
         List<Cell> cells = (List<Cell>) toList(row.cellIterator());

         if (cells.size() > nomeIndex && cells.size() > emailIndex) {
            File fileCheck = File.builder()
                  .name(cells.get(nomeIndex).getStringCellValue())
                  .email(cells.get(emailIndex).getStringCellValue())
                  .build();

            files.add(fileCheck);
         }
      });

      return files;
   }

   private int findEmailColumnIndex(List<Cell> headerCells) {
      for (int i = 0; i < headerCells.size(); i++) {
         Cell cell = headerCells.get(i);
         String columnName = cell.getStringCellValue().trim().toLowerCase();
         if ("email".equals(columnName) || "e-mail".equals(columnName)) {
            return i;
         }
      }
      return -1;
   }

   private int findColumnIndex(List<Cell> headerCells, String columnName) {
      for (int i = 0; i < headerCells.size(); i++) {
         Cell cell = headerCells.get(i);
         if (columnName.equalsIgnoreCase(cell.getStringCellValue().trim())) {
            return i;
         }
      }
      return -1;
   }

   public List<?> toList(Iterator<?> iterator) {
      return IteratorUtils.toList(iterator);
   }

   public void print(List<File> files) {
      files.forEach(file -> {
         System.out.println(file.getName());
         System.out.println(file.getEmail());
      });
   }

   public List<String> extractData(List<File> files) {
      List<String> extractedData = new ArrayList<>();
      for (File file : files) {
         extractedData.add(file.getEmail());
      }
      return extractedData;
   }
}
