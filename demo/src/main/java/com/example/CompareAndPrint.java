package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompareAndPrint {
   public static void compare(List<String> data1, List<String> data2, String outputPath) {
      List<String> lowercasedData1 = data1.stream().map(String::toLowerCase).collect(Collectors.toList());
      List<String> lowercasedData2 = data2.stream().map(String::toLowerCase).collect(Collectors.toList());

      Collections.sort(lowercasedData1);
      Collections.sort(lowercasedData2);

      List<String> commonItems = new ArrayList<>();
      for (String item : lowercasedData1) {
         boolean found = BinarySearch.search(lowercasedData2, item);
         if (found) {
            commonItems.add(item);
         }
      }

      List<String> differentItems1 = new ArrayList<>();
      List<String> differentItems2 = new ArrayList<>();

      for (String item : lowercasedData1) {
         if (!BinarySearch.search(lowercasedData2, item)) {
            if (!item.isEmpty()) {
               differentItems1.add(item);
            }
         }
      }

      for (String item : lowercasedData2) {
         if (!BinarySearch.search(lowercasedData1, item) && !differentItems1.contains(item)) {
            if (!item.isEmpty()) {
               differentItems2.add(item);
            }
         }
      }

      ExcelWriter.writeResultsToExcel(outputPath, commonItems, differentItems1, differentItems2);
   }
}
