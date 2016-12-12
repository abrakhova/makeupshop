package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import fi.haagahelia.course.domain.ProductRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.Product;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	
	@Autowired
	private ProductRepository repository;
	
	
	
	@Test
    public void findBySkuShouldReturnProduct() {
        List<Product> products = repository.findBySku("1001");
        
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Fabulous");
    }
	
	
	@Test
    public void createProduct() {
    	Product product = new Product("Colossal","Maybelline","black",450,"1010","images/lipstick1.jpg", new Category("Waterproof"));
    	repository.save(product);
    	assertThat(product.getId()).isNotNull();
    }   
	
}
