package com.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class File {

   private String name;
   private String email;
}
