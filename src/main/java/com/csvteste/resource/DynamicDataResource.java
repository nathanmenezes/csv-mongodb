package com.csvteste.resource;

import com.csvteste.model.DynamicDataEntity;
import com.csvteste.repository.DynamicDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

@RestController
@RequestMapping("/dynamic-data")
public class DynamicDataResource {

    @Autowired
    DynamicDataRepository dynamicDataRepository;

    @GetMapping
    public List<DynamicDataEntity> getDynamicData() {
        String csvFile = "C:\\Users\\Nathan\\Desktop\\TESTECSVPRODUCT.csv";
        Map<Long, Map<String, String>> item = new HashMap<>();
        List<DynamicDataEntity> documents = new ArrayList<>();

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                List<String> headersList = Arrays.asList(csvParser.getHeaderNames().get(0).split(";"));
                long i = csvRecord.getRecordNumber();
                int j = 0;
                Map<String, String> itemMap = new HashMap<>();
                String[] list = csvRecord.get(csvParser.getHeaderNames().get(0)).split(";");
                for (String csvElement : list) {
                    itemMap.put(headersList.get(j), csvElement);
                    j++;
                }
                item.put(i, itemMap);
                ObjectMapper objectMapper = new ObjectMapper();
                String jacksonData = objectMapper.writeValueAsString(itemMap);
                documents.add(new DynamicDataEntity(Document.parse(jacksonData)));
            }
            System.out.println(item);
            System.out.println(documents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dynamicDataRepository.saveAll(documents);
        return dynamicDataRepository.findAll();
    }
}
