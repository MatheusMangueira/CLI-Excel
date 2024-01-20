package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            List<String> dataFromPathName = fetchData(pathName);
            List<String> dataFromOption2 = fetchData(option2);

            // Especifique o caminho do arquivo de saída aqui
            // Comparar os dados e imprimir os resultados
            // compareAndPrint(dataFromPathName, dataFromOption2);
            compareAndPrint(dataFromPathName, dataFromOption2, exit);

        } else {
            System.out.println("É necessário fornecer valores para ambos -n e -2.");
        }
    }

    private List<String> fetchData(String path) {
        FileReading fileReading = new FileReading();
        try {
            List<File> files = fileReading.readFiles(path);
            return fileReading.extractData(files);
        } catch (IOException e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
        }
        return new ArrayList<>(); // Retorna uma lista vazia se não houver dados
    }

    private void compareAndPrint(List<String> data1, List<String> data2, String outputPath) {
        // Converter todos os e-mails para minúsculas antes da comparação
        List<String> lowercasedData1 = data1.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> lowercasedData2 = data2.stream().map(String::toLowerCase).collect(Collectors.toList());

        List<String> commonItems = lowercasedData1.stream()
                .filter(lowercasedData2::contains)
                .collect(Collectors.toList());

        List<String> differentItems = new ArrayList<>(lowercasedData1);
        differentItems.removeAll(commonItems);

        differentItems.addAll(lowercasedData2.stream()
                .filter(item -> !commonItems.contains(item))
                .collect(Collectors.toList()));

        ExcelWriter.writeResultsToExcel(outputPath, commonItems, differentItems);
    }

}

