package com.exam.tournament.service;

import com.exam.tournament.dataProviders.FileDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FilesServiceTest {
    @Autowired
    private FilesService filesService;
    @Autowired
    private FileDataProvider fileDataProvider;

    @Test
    void getFileNames() {
        Set<File> files = filesService.getFileNames();
        assertEquals(3, files.size());
    }

    @Test
    void readCSV() {
        List<List<String>> data = filesService.readCSV(fileDataProvider.getBasketBallTestFile());
        assertEquals(7, data.size());
        assertEquals("BASKETBALL", data.get(0).get(0));
    }

    @Test
    void identifyFileType() {
        String csvFormat = "csv";
        Set<File> files = filesService.getFileNames();
        files.stream()
                .map(filesService::identifyFileType)
                .forEach(format->assertEquals(csvFormat,format));
    }
}