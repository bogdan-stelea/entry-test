package io.arxall.entrytest.repository;

import io.arxall.entrytest.model.ProductElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.domain.Pageable;

public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, String> {

    Page<ProductElastic> findByName(String name, Pageable pageable);

}
