package com.example;

import java.io.IOException;
import java.util.List;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "hello", description = "path name")

public class Main implements Runnable {

    @Option(names = { "-n", "--pathName" }, description = "print pathName")
    private String pathName;
    @Option(names = { "-2", "--option2" }, description = "Option 2")
    private String option2;
    @Option(names = { "-s", "--exit" }, description = "Exit")
    private String exit;

    public static void main(String[] args) throws IOException {
        CommandLine commandLine = new CommandLine(new Main());
        commandLine.execute(args);
    }

    @Override
    public void run() {
        if (pathName != null && option2 != null && exit != null) {
            List<String> dataFromPathName = FetchData.fetch(pathName);
            List<String> dataFromOption2 = FetchData.fetch(option2);

            CompareAndPrint.compare(dataFromPathName, dataFromOption2, exit);

        } else {
            System.out.println("É necessário fornecer valores para ambos -n, -2 e -s.");
        }
    }
}