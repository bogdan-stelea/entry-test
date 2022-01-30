package io.arxall.entrytest.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blog", type = "article")
public class ProductElastic extends Product {

}
