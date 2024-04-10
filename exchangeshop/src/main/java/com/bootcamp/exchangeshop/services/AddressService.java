package com.bootcamp.exchangeshop.services;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.exchangeshop.models.Address;
import com.bootcamp.exchangeshop.models.User;
import com.bootcamp.exchangeshop.repositories.AddressRepository;

@Service
public class AddressService {
		
				private final AddressRepository addRepo;
				
				public AddressService(AddressRepository addRepo) {
					this.addRepo = addRepo;
				}
				
				// FIND ALL
//				public List<Address> allAddresses(){
//					return addRepo.findAll();
//				}
				
				// FIND ONE
				public Address oneAdd(Long id) {
					Optional<Address> optionalAdd = addRepo.findById(id);
					if(optionalAdd.isPresent()) {
						return optionalAdd.get();
					}
					else {
						return null;
					}
				}
				
				// CREATE
				public Address createAdd(Address newAdd) {
//					newAdd.setResident(resident);
					return addRepo.save(newAdd);
				}
				
				// UPDATE
				public Address updateAdd(Address oneAdd) {
					return addRepo.save(oneAdd);

				}
				
				// DELETE
				public void deleteAdd(Long id) {
					addRepo.deleteById(id);
				}
}
