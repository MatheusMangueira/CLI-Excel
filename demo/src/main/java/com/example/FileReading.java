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

//"E:\\Estudos\\cli\\demo\\src\\main\\resources\\teste_monise.xlsx"
public class FileReading {

   public List<File> readFiles(String path) throws IOException {

      List<File> files = new ArrayList<>();

      // recuperando o arquivo
      FileInputStream file = new FileInputStream(path);

      // setando a aba do arquivo
      Workbook workbook = new XSSFWorkbook(file);
      Sheet sheet = workbook.getSheetAt(0);

      // setando as linhas do arquivo
      List<Row> rows = (List<Row>) toList(sheet.iterator());

      // Obter o cabeçalho da primeira linha
      List<Cell> headerCells = (List<Cell>) toList(rows.get(0).cellIterator());

      // Verificar se as colunas "Nome" e "Email" estão presentes no cabeçalho
      int nomeIndex = findColumnIndex(headerCells, "nome");
      int emailIndex = findEmailColumnIndex(headerCells);

      if (nomeIndex == -1 || emailIndex == -1) {
         throw new IllegalArgumentException("As colunas 'Nome' e 'Email' são necessárias no arquivo Excel.");
      }

      // Remover a linha do cabeçalho
      rows.remove(0);

      rows.forEach(row -> {
         // setando as celulas
         List<Cell> cells = (List<Cell>) toList(row.cellIterator());

         // Verificar se as células de "Nome" e "Email" estão presentes antes de criar o
         // objeto File
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
      return -1; // Retorna -1 se a coluna "email" ou "e-mail" não for encontrada
   }

   private int findColumnIndex(List<Cell> headerCells, String columnName) {
      for (int i = 0; i < headerCells.size(); i++) {
         Cell cell = headerCells.get(i);
         if (columnName.equalsIgnoreCase(cell.getStringCellValue().trim())) {
            return i;
         }
      }
      return -1; // Retorna -1 se a coluna não for encontrada
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
         // extractedData.add(file.getName());
         extractedData.add(file.getEmail());
         // Ajuste conforme necessário para extrair os dados desejados
      }
      return extractedData;
   }

}
