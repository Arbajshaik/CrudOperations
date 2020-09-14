package com.example.Crudproj;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import service.Product;
import service.ProductRepository;
import service.ProductServiceImpl;

//@SpringBootConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes  = CrudprojApplicationTests.class)
public class ServiceTest {
	private MockMvc mockMvc;
	@InjectMocks
	ProductServiceImpl serviceimp ;
	
	
	@Mock
	ProductRepository repo;
	
	
	 @Test
	   public void getAllProduct() {
		 List<Product> list =new ArrayList<Product>();
			Product idone = new Product();
			Product idtwo = new Product();
			Product idthree = new Product();
			
			list.add(idone);
			list.add(idtwo);
			list.add(idthree);
			
			when(serviceimp.getAllProducts()).thenReturn(list);
			serviceimp.getAllProducts();
			
	 }
	 @Test
		public void getProductById()
		
		{
		 Product product = new Product();
		 product.setDesciption("description");
		 Optional<Product> productDb = Optional.ofNullable(product);
		 when(repo.findById((long) 12345)).thenReturn( productDb);
		Product response= serviceimp.getProductById(12345);
		assertEquals("description", product.getDesciption());  
			}
	 @Test
	 public void createProduct()
		{
	      Product product = new Product();
	      product.setDesciption("description");
	      Product response=serviceimp.createProduct(product);
	      assertEquals("description", product.getDesciption());  
		}
	 @Test
		public void deleteProduct() {
		 Product product = new Product();
		 product.setDesciption("description");
		 Optional<Product> productDb = Optional.ofNullable(product);
		 when(repo.findById((long) 12345)).thenReturn( productDb);
		 serviceimp.deleteProduct(12345);
			assertEquals("description", product.getDesciption());
		}		
}

