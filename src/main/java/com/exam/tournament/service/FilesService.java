package com.exam.tournament.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
@PropertySource("classpath:values.properties")
public class FilesService {
    @Value("${files.location}")
    private String dir;

    private static final String  COMMA_DELIMITER = ";";

    /**
     * Gets set of files, that located in directory
     * @return Set of files in directory
     */
    public Set<File> getFileNames(){
        log.debug("Get files names call");
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getAbsoluteFile)
                .collect(Collectors.toSet());
    }

    /**
     * Reads one file with game information and returns game data.
     * @param file
     * @return List<List<String>>
     *     High level list contains a list of values;
     */
    @SneakyThrows
    public List<List<String>> readCSV(File file){

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public String identifyFileType(File file)
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else {
            log.error("Could not identify type of file {}", fileName);
            throw new IllegalArgumentException(String.format("Could not identify type of file %s", fileName));
        }
    }
}
