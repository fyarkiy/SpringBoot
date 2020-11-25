package ma.boot.springboot.repository;

import java.util.List;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.dto.ProductResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT "
            + "new ma.boot.springboot.model.dto.ProductResponseDto(p.productId) "
            + " from Review r, Product p "
            + "where r.product = p.id GROUP BY r.product "
            + "ORDER BY COUNT(r.product) DESC, p.productId ASC")
    List<ProductResponseDto> getTopProducts(Pageable pageable);
}
