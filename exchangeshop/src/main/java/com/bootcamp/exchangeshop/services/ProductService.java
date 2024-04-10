package com.bootcamp.exchangeshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.exchangeshop.models.Product;
import com.bootcamp.exchangeshop.repositories.ProductRepository;

@Service
public class ProductService {
	
			private final ProductRepository prodRepo;
			
			public ProductService(ProductRepository prodRepo) {
				this.prodRepo = prodRepo;
			}
			
			// FIND ALL
			public List<Product> allProducts(){
				return prodRepo.findAll();
			}
			
			// FIND ALL BY CATEGORY

			public List<Product> allProductsbyCategory(String category){
				return prodRepo.findByProductcategory(category);
			}
			
			// FIND ONE
			public Product oneProd(Long id) {
				Optional<Product> optionalProd = prodRepo.findById(id);
				if(optionalProd.isPresent()) {
					return optionalProd.get();
				}
				else {
					return null;
				}
			}
			
			// CREATE
			public Product createProd(Product newProd) {
				return prodRepo.save(newProd);
			}
			
			// UPDATE
			public Product updateProd(Product oneProd) {
				return prodRepo.save(oneProd);

			}
			
			// DELETE
			public void deleteProd(Long id) {
				prodRepo.deleteById(id);
			}
}
