package com.exam.tournament.service;

import com.exam.tournament.dataProviders.FileDataProvider;
import com.exam.tournament.dataProviders.WrongFileDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceTest {

    @Autowired
    private FileDataProvider fileDataProvider;

    @Autowired
    private WrongFileDataProvider wrongFileDataProvider;

    @Autowired
    private ValidationService validationService;

    @Test
    void validateForCSVCorrectFile() {
        File testFile = fileDataProvider.getBasketBallTestFile();
        assertDoesNotThrow(()->validationService.validateForCSV(testFile));
    }
    @Test
    void validateForCSVWrongFileFormat() {
        File testFile = wrongFileDataProvider.getWrongTestFile();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->validationService.validateForCSV(testFile));
        assertEquals("Type txt not supported", ex.getMessage());
    }
}