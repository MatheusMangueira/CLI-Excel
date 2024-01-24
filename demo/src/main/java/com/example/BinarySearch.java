package com.example;

import java.util.List;

public class BinarySearch {
   public static boolean search(List<String> sortedList, String target) {
      int low = 0;
      int high = sortedList.size() - 1;

      while (low <= high) {
         int mid = (low + high) / 2;
         String midItem = sortedList.get(mid);

         if (midItem.equals(target)) {
            return true;
         } else if (midItem.compareTo(target) < 0) {
            low = mid + 1;
         } else {
            high = mid - 1;
         }
      }

      return false;
   }
}
