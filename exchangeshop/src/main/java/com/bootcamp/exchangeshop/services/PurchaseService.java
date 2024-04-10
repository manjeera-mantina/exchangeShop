package com.bootcamp.exchangeshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.exchangeshop.models.Purchase;
import com.bootcamp.exchangeshop.repositories.PurchaseRepository;

@Service
public class PurchaseService {

				private final PurchaseRepository purchaseRepo;
				
				public PurchaseService(PurchaseRepository purchaseRepo) {
					this.purchaseRepo = purchaseRepo;
				}
				
				// FIND ALL
				public List<Purchase> allPurchases(){
					return purchaseRepo.findAll();
				}
				
				// FIND ONE
				public Purchase onePurch(Long id) {
					Optional<Purchase> optionalPurchase = purchaseRepo.findById(id);
					if(optionalPurchase.isPresent()) {
						return optionalPurchase.get();
					}
					else {
						return null;
					}
				}
				
				// CREATE
				public Purchase createPurch(Purchase newPurch) {
					return purchaseRepo.save(newPurch);
				}
				
				// UPDATE
				public Purchase updatePurch(Purchase onePurch) {
					return purchaseRepo.save(onePurch);

				}
				
				// DELETE
				public void deletePurch(Long id) {
					purchaseRepo.deleteById(id);
				}
}
