package ma.boot.springboot.controller;

import java.util.List;
import ma.boot.springboot.model.dto.WordResponseDto;
import ma.boot.springboot.service.WordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordController {
    private static final String QUANTITY = "100";
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/top")
    public List<WordResponseDto> getTopWords(@RequestParam(defaultValue = QUANTITY) int quantity) {
        return wordService.getTopWords(quantity);
    }
}
