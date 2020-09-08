package service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import service.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
