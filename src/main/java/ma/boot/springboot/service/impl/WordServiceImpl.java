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
    public Word add(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Word get(Long id) {
        return wordRepository.findById(id).get();
    }

    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> addAll(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public List<WordResponseDto> getTopWords(int quantity) {
        return wordRepository.getTopWords(PageRequest.of(0, quantity));
    }
}
