package com.csvteste.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Nathan\\Desktop\\TESTECSVPRODUCT.csv";
        String headers = "CODIGO;NOME;MARCA;TAMANHO;PRECO;VARIACAO;QUANTIDADE;CATEGORIA";
        Map<Long, Map<String, String>> item = new HashMap<>();

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                List<String> headersList = Arrays.asList(csvParser.getHeaderNames().get(0).split(";"));
                long i = csvRecord.getRecordNumber();
                int j = 0;
                Map<String, String> itemMap = new HashMap<>();
                String[] list = csvRecord.get(headers).split(";");
                for (String csvElement : list) {
                    itemMap.put(headersList.get(j), csvElement);
                    j++;
                }
                item.put(i, itemMap);
                ObjectMapper objectMapper = new ObjectMapper();
                String jacksonData = objectMapper.writeValueAsString(itemMap);
                System.out.println(jacksonData);
            }
            System.out.println(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
