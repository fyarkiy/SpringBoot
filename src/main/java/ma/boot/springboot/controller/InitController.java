package ma.boot.springboot.controller;

import java.io.IOException;
import java.util.List;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.service.AdderDataToDb;
import ma.boot.springboot.service.read.ReadingCsvFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    private static final String UPLOAD_FILE = "new_short_file.csv";
    private final AdderDataToDb adderDataToDb;

    public InitController(AdderDataToDb adderDataToDb) {
        this.adderDataToDb = adderDataToDb;
    }

    @GetMapping("/start")
    public String uploadData() throws IOException {
        List<ReviewDto> dtos = ReadingCsvFile.readWithCsvBeanReader(UPLOAD_FILE);
        adderDataToDb.addingDataToStorage(dtos);
        return "file was loaded";
    }
}
