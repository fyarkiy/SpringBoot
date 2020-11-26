package ma.boot.springboot.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "the_word")
    private String value;
    private Long occurrence;

    public Word(String value) {
        this.value = value;
    }

    public Word(String value, Long occurrence) {
        this.value = value;
        this.occurrence = occurrence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o.getClass().equals(Word.class))) {
            return false;
        }
        Word word = (Word) o;
        return getValue().equals(word.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
