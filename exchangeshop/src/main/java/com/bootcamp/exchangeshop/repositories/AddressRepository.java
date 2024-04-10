package com.bootcamp.exchangeshop.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.exchangeshop.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
		
//		List<Address> findAll();
		
}
