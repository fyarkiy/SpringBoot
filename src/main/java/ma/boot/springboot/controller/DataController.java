package ma.boot.springboot.controller;

import java.io.IOException;
import java.util.List;
import ma.boot.springboot.model.dto.ReviewRequestDto;
import ma.boot.springboot.service.DatabaseSaver;
import ma.boot.springboot.service.read.ReadingCsvFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    private static final String UPLOAD_FILE = "new_short_file.csv";
    private final DatabaseSaver databaseSaver;

    public DataController(DatabaseSaver databaseSaver) {
        this.databaseSaver = databaseSaver;
    }

    @GetMapping("/start")
    public String uploadData() throws IOException {
        List<ReviewRequestDto> dtos = ReadingCsvFile.readWithCsvBeanReader(UPLOAD_FILE);
        int records = databaseSaver.addDataToStorage(dtos);
        return "file was loaded with total " + records + " number of records.";
    }
}
