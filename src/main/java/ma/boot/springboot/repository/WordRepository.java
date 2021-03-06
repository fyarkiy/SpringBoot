package ma.boot.springboot.repository;

import java.util.List;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.WordResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT "
            + "new ma.boot.springboot.model.dto.WordResponseDto(w.value, w.occurrence) "
            + " from Word w "
            + " Order BY w.occurrence DESC, w.value ASC")
    List<WordResponseDto> findMostUsedWords(Pageable pageable);
}
