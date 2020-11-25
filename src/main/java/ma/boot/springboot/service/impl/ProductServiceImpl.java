package ma.boot.springboot.service.impl;

import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.dto.ProductResponseDto;
import ma.boot.springboot.repository.ProductRepository;
import ma.boot.springboot.service.ProductService;
import ma.boot.springboot.service.mapper.ProductMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Product add(ReviewDto dto) {
        return productRepository
                .save(productMapper.mapReviewDtoToProduct(dto));
    }

    @Override
    public List<Product> addAll(Set<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<ProductResponseDto> getTopProducts(int quantity) {
        return productRepository.getTopProducts(PageRequest.of(0,quantity));
    }

    @Override
    public Product get(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
