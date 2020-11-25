package ma.boot.springboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordResponseDto {
    private String value;
    private Long occurrence;
}
