package com.microservices.bookstore.catalog_service.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.microservices.bookstore.catalog_service.entities.Product;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:mysql:8.4:///db"})
@Sql("/test-data.sql")
// @Import(TestcontainersConfiguration.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    void shouldGetProductByCode() {
        Product product = productRepository.findByCode("P100").get();
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("The Hunger Games");
    }

    @Test
    void shouldThrowExceptionForGetProductByInvalidCode() {
        assertThat(productRepository.findByCode("P1000")).isEmpty();
    }
}
