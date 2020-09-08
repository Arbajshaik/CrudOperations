package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.Optional;

import exception.ResourceNotFoundException;


@Service
@Transactional

public class ProductServiceImpl implements ProductService {

	
	private ProductRepository productRepository;

	
	  public ProductServiceImpl(ProductRepository productRepository) {
	  this.productRepository=productRepository; 
	  }
	 
	public Product createProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product>  productDb = this.productRepository.findById(product.getId());
//	Optional<Product> productDb =this.productRepository.findAll()
	if (productDb.isPresent()) {
		Product productUpdate = productDb.get();
		productUpdate.setId(product.getId());
		productUpdate.setName(product.getName());
		productUpdate.setDesciption(product.getDesciption());
		productRepository.save(productUpdate);
		return productUpdate;
	}else {
          throw new ResourceNotFoundException("Record not found with id :" +product.getId());
	}
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) {
       Optional<Product> productDb =this.productRepository.findById(productId);	
       if(productDb.isPresent()) {
    	   return productDb.get();
       }else {
    	   throw new ResourceNotFoundException("Record not found with id : "+productId);
       
       }
	}

	@Override
	public void deleteProduct(long productId) {
	 Optional<Product> productDb =this.productRepository.findById(productId);
	    
	     if(productDb.isPresent()) {
	    	 this.productRepository.delete(productDb.get());
	       }else {
	    	   throw new ResourceNotFoundException("Record not found with id : "+productId);
	       
	       }
		
	}
	

}
