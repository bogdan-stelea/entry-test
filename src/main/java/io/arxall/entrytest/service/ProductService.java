package io.arxall.entrytest.service;

import io.arxall.entrytest.model.Product;
import io.arxall.entrytest.model.ProductElastic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    ProductElastic createProduct(ProductElastic productElastic);

    Product updateProduct(Product product);

    ProductElastic updateProductElastic(ProductElastic productElastic);

    List<Product> getAllProduct();

    List<ProductElastic> getAllProductElastic();

    Product getProductById(String productId);

    ProductElastic searchById(String productId);

    Page<ProductElastic> searchByName(String name, Pageable pageable);

    ResponseEntity<String> deleteProduct(String id);

    ResponseEntity<String> deleteElasticProduct(String id);
}