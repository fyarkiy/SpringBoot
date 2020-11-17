package ma.boot.springboot.service.mapper;

import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product mapReviewDtoToProduct(ReviewDto dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        return product;
    }
}
