package com.bootcamp.exchangeshop.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.exchangeshop.models.Purchase;


@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

		List<Purchase> findAll();
}
