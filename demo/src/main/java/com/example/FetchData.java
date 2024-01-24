package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchData {
   public static List<String> fetch(String path) {
      FileReading fileReading = new FileReading();
      try {
         List<File> files = fileReading.readFiles(path);
         return fileReading.extractData(files);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return new ArrayList<>();
   }
}
