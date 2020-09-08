package com.example.Crudproj;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.mapping.model.IdPropertyIdentifierAccessor;

import controller.ProductController;
import service.Product;
import service.ProductService;

@RunWith(PowerMockRunner.class)
//@PrepareForTest(fullyQualifiedNames = "com.example.Crudproj")

public class ControllerTest {
    
	@InjectMocks
	ProductController controller;
	
	
	@Mock
	ProductService service;
	
	//@Before 
	//public void init() {
		//MockitoAnnotations.initMocks(this);
	//}
	@Test
	public void getAllProduct()
	{
		List<Product> list =new ArrayList<Product>();
		Product idone = new Product();
		Product idtwo = new Product();
		Product idthree = new Product();
		
		list.add(idone);
		list.add(idtwo);
		list.add(idthree);
		
		when(service.getAllProducts()).thenReturn(list);
		controller.getAllProduct();
	}
	@Test
	public void getProductById()
	
	{
		when(service.getProductById(1)).thenReturn(new Product());
		controller.getProductById(1);
		}
	@Test
	public void createProduct()
	{
      Product id = new Product();
      controller.createProduct(id);
      verify(service,times(1)).createProduct(id);
	}
	@Test
	public void updateProduct()
	{
	
	}
	@Test
	public void deleteProduct() {
		
	}
	

}
