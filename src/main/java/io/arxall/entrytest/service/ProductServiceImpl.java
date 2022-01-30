package io.arxall.entrytest.service;

import io.arxall.entrytest.exception.ResourceNotFoundException;
import io.arxall.entrytest.model.Product;
import io.arxall.entrytest.model.ProductElastic;
import io.arxall.entrytest.repository.ProductElasticRepository;
import io.arxall.entrytest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductElasticRepository productElasticRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductElastic createProduct(ProductElastic productElastic) {
        return productElasticRepository.save(productElastic);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());

        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + product.getId());
        }
    }

    @Override
    public ProductElastic updateProductElastic(ProductElastic productElastic) {
        Optional<ProductElastic> productDb = this.productElasticRepository.findById(productElastic.getId());

        if (productDb.isPresent()) {
            ProductElastic productUpdate = productDb.get();
            productUpdate.setId(productElastic.getId());
            productUpdate.setName(productElastic.getName());
            productUpdate.setDescription(productElastic.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + productElastic.getId());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public List<ProductElastic> getAllProductElastic() {
        return (List<ProductElastic>) this.productElasticRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {

        Optional<Product> productDb = this.productRepository.findById(productId);

        if (productDb.isEmpty()) {
            throw new ResourceNotFoundException("Record not found with id : " + productId);

        }

        return productDb.get();
    }

    @Override
    public ProductElastic searchById(String productId) {
        Optional<ProductElastic> product = productElasticRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Record not found with id : " + productId);
        }

        return product.get();
    }

    @Override
    public Page<ProductElastic> searchByName(String name, Pageable pageable) {
        Page<ProductElastic> product = productElasticRepository.findByName(name, pageable);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Record not found with name : " + name);
        }

        return product;
    }

    @Override
    public ResponseEntity<String> deleteProduct(String productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);

        if (productDb.isEmpty()) {
            throw new ResourceNotFoundException("Record not found with id : " + productId);

        }
        this.productRepository.delete(productDb.get());
        return ResponseEntity.ok("Successfully deleted");
    }

    @Override
    public ResponseEntity<String> deleteElasticProduct(String productId) {
        Optional<ProductElastic> product = this.productElasticRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Record not found with id : " + productId);
        }
        this.productElasticRepository.delete(product.get());
        return ResponseEntity.ok("Successfully deleted");
    }
}