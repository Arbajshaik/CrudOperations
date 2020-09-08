package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Product;
import service.ProductService;



@RequestMapping("/testing")
@Controller

public class ProductController {
  
	@Autowired
	private ProductService productService;
	
	@GetMapping("*")
	 public ResponseEntity<List<Product>> getAllProduct(){
	 return ResponseEntity.ok().body(productService.getAllProducts());
 }
	
	@GetMapping("/products/{id}")
	 public ResponseEntity<Product> getProductById(@PathVariable long id){
	 return ResponseEntity.ok().body(productService.getProductById(id));
	}
	
	
	@PostMapping(path="/product" ,consumes="application/json")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.ok().body(this.productService.createProduct(product));
		
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody Product product){
		product.setId(id);
		return ResponseEntity.ok().body(this.productService.updateProduct(product));
	}
  @ResponseBody
  @DeleteMapping("/products/{id}")
	public HttpStatus deleteProduct(@PathVariable long id){
		this.productService.deleteProduct(id);
		return HttpStatus.OK;
		
	}
}

