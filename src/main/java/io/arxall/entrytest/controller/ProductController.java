package io.arxall.entrytest.controller;

import io.arxall.entrytest.model.Product;
import io.arxall.entrytest.model.ProductElastic;
import io.arxall.entrytest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/view/products/{id}")
    public ResponseEntity<ProductElastic> searchById(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.searchById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(this.productService.createProduct(product));
    }

    @PostMapping("/view/products")
    public ResponseEntity<ProductElastic> createProductElastic(@RequestBody ProductElastic productElastic) {
        return ResponseEntity.ok().body(this.productService.createProduct(productElastic));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }

    @PutMapping("/view/products/{id}")
    public ResponseEntity<ProductElastic> updateProductElastic(@PathVariable String id, @RequestBody ProductElastic productElastic) {
        productElastic.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProductElastic(productElastic));
    }

    @DeleteMapping("/products/{id}")
    public HttpStatus deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/view/products/{id}")
    public HttpStatus deleteElasticProduct(@PathVariable String id) {
        this.productService.deleteElasticProduct(id);
        return HttpStatus.OK;
    }

}