package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.WordResponseDto;

public interface WordService {
    List<Word> addAll(List<Word> words);

    List<WordResponseDto> findMostUsedWords(int quantity);
}
