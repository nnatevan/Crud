package az.rest.app;

import java.util.List;
import java.util.Optional;


public interface ProductService{
    void save(Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
