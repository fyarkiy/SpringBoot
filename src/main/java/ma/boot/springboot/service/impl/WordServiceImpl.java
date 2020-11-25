package ma.boot.springboot.service.impl;

import java.util.List;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.WordResponseDto;
import ma.boot.springboot.repository.WordRepository;
import ma.boot.springboot.service.WordService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> addAll(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public List<WordResponseDto> findMostUsedWords(int quantity) {
        return wordRepository.findMostUsedWords(PageRequest.of(0, quantity));
    }
}
