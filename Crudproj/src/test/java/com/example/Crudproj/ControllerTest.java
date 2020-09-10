package com.example.Crudproj;


import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ProductController;
import service.Product;
import service.ProductService;




@SpringBootConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes  = CrudprojApplicationTests.class)
//@PrepareForTest(fullyQualifiedNames = "com.example.Crudproj")

public class ControllerTest {
    private MockMvc mockMvc;
	@InjectMocks
	ProductController controller;
	
	
	@Mock
	ProductService service;
	
	//@Before 
	//public void init() {
		//MockitoAnnotations.initMocks(this);
	//}
	/*
	 * @Test public void getAllProduct() { List<Product> list =new
	 * ArrayList<Product>(); Product idone = new Product(); Product idtwo = new
	 * Product(); Product idthree = new Product();
	 * 
	 * list.add(idone); list.add(idtwo); list.add(idthree);
	 * 
	 * when(service.getAllProducts()).thenReturn(list); controller.getAllProduct();
	 * }
	 */


	/*
	 * private ArrayList productList;
	 * 
	 * @BeforeEach public void setUp() { this.productList = new ArrayList<>();
	 * this.productList.add(new Product (1,"name1","desciption1","10.0"));
	 * this.productList.add(new Product(2,"name2","desciption2","20.0")); }
	 * 
	 * @Test public void getAllProduct() throws Exception{
	 * given(productService.getAllProducts()).willReturn(productList);
	 * this.mockMvc.perform(get("/testing/product")) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.size()",is (productList.size()))); }
	 */

	
	private static final MediaType APPLICATION_JSON_VALUE = null;
	@Autowired
	private WebApplicationContext wc;
	
	 
	List<Product> products = new ArrayList<Product>();
ObjectMapper mAPPER =new ObjectMapper();
protected MockMvc mvc;
	@Before
	 public void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(wc).build();
	   }
	public String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	  public <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
	
   @Test
   public void getAllProduct() throws Exception{
	   String uri ="/testing/product";
	   MvcResult result =mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
int Status=result.getResponse().getStatus();
	   String content = result.getResponse().getContentAsString();
	 assertNotNull(content);  
   }
   
	/*
	 * public void getAllProduct() throws JsonProcessingException,Exception {
	 * 
	 * Product product = new Product(); product.setId(1); product.setName("name");
	 * product.setDesciption("desciption"); mAPPER.writeValueAsString(product);
	 * MvcResult result = (MvcResult) mockMvc.perform(get("/testing/product")
	 * .contentType(APPLICATION_JSON_VALUE)) .andExpect(status().isOk())
	 * .andReturn(); List<Product> response =mAPPER.readValue(result.getResponse()
	 * .getContentAsString(), new TypeReference<List<Product>>() { } ); //
	 * assertEquals(product,response); }
	 */

	
	@Test
	public void createProduct() throws  Exception
	{
		 String uri = "/testing/product";
		   Product product = new Product();
		   product.setId(1);
		   product.setName("Ginger");
		   product.setDesciption("desciption");
		   
		   String inputJson = mapToJson(product);
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		  		  
		   String content = mvcResult.getResponse().getContentAsString();
		   assertNotNull(content); 
	}
	
	@Test
	public void updateProduct() throws Exception {
		 String uri = "/testing/products/1";
		   Product product = new Product();
		   product.setName("name");
		   
		   String inputJson = mapToJson(product);
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		     String content = mvcResult.getResponse().getContentAsString();
			   assertNotNull(content); 

		}
	
	@Test
	public void deleteProduct() throws Exception {
		 Product product = new Product();
		 String uri = "//testing/products/1";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		   
		   String content = mvcResult.getResponse().getContentAsString();
		   assertNotNull(content); 
	}
	

}
