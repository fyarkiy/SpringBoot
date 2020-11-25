package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.dto.ProductResponseDto;

public interface ProductService extends GenericService<Product> {
    List<ProductResponseDto> getTopProducts(int quantity);
}
