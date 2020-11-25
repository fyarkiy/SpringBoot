package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.WordResponseDto;

public interface WordService {
    Word add(Word item);

    Word get(Long id);

    List<Word> getAll();

    List<Word> addAll(List<Word> words);

    List<WordResponseDto> getTopWords(int quantity);
}
