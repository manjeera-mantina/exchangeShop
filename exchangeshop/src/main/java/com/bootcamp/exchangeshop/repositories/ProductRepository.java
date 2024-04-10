package com.bootcamp.exchangeshop.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.exchangeshop.models.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	List<Product> findAll();
	List<Product> findByProductcategory(String category);
	
}

			
