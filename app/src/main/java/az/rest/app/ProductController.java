package az.rest.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class ProductController {
    private final az.rest.app.ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getProductList() {
        return productService.findAll();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable(value = "productId") Long productId) {
        return productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

    @PostMapping("/product")
    public String createProduct(@RequestBody Product product) {
        productService.save(product);
        return "Product added";
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody Product product) {
        return productService.findById(productId).map(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            productService.save(p);
            return "Product updated";
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable(value = "productId") Long productId) {
        return productService.findById(productId).map(p -> {
            productService.deleteById(productId);
            return "Product deleted";
        }).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
    }
}
