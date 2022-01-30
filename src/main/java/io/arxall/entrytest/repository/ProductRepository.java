package io.arxall.entrytest.repository;

import io.arxall.entrytest.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}