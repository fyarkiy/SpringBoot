package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.dto.ProductRequestDto;
import ma.boot.springboot.model.dto.ProductResponseDto;

public interface ProductService extends GenericService<Product, ProductRequestDto> {
    List<ProductResponseDto> findMostReviewedProducts(int quantity);
}
