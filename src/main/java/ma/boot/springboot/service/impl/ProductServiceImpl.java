package ma.boot.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.repository.ProductRepository;
import ma.boot.springboot.service.ProductService;
import ma.boot.springboot.service.mapper.ProductMapper;
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
        return productRepository.save(productMapper.mapReviewDtoToProduct(dto));
    }

    @Override
    public List<Product> addAll(List<ReviewDto> dtoList) {
        List<Product> products = dtoList.stream()
                .map(productMapper::mapReviewDtoToProduct)
                .collect(Collectors.toList());
        return productRepository.saveAll(products);
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
