package ma.boot.springboot.service.mapper;

import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.dto.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapDtoToProduct(ProductRequestDto dto) {
        return new Product(dto.getProductId());
    }
}
