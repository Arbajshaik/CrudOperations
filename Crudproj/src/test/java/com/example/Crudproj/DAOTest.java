package com.example.Crudproj;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.Product;
import service.ProductRepository;

@SpringBootApplication
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@RunWith(SpringRunner.class)
public class DAOTest   {
	@Autowired
	private ProductRepository productRepo;
	
	Product product = new Product();
	@Test
	public void testgetAllProduct() {
		List<Product>product =productRepo.findAll();
		assertEquals(product.get(0).getDesciption(),"cg12");
	}
	@Test
	public void testgetProductById() {
	assertEquals(productRepo.findById((long) 1).get().getDesciption(),"hello");	
	}
	@Test
	public void createProduct() {
		assertNotNull(productRepo.save(product));
	}
	
	
}
