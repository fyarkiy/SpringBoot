package ma.boot.springboot.service.mapper;

import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.WordResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {
    public WordResponseDto mapWordToDto(Word word) {
        return new WordResponseDto(word.getTheWord());
    }
}
