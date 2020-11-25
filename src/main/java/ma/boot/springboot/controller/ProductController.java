package ma.boot.springboot.controller;

import java.util.List;
import ma.boot.springboot.model.dto.ProductResponseDto;
import ma.boot.springboot.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String QUANTITY = "1000";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/top")
    List<ProductResponseDto> getMostReviewedProducts(@RequestParam(defaultValue = QUANTITY)
                                                   int quantity) {
        return productService.findMostReviewedProducts(quantity);
    }
}
