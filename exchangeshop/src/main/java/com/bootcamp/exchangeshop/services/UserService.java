package com.bootcamp.exchangeshop.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bootcamp.exchangeshop.models.Address;
import com.bootcamp.exchangeshop.services.AddressService;
import com.bootcamp.exchangeshop.models.LoginUser;
import com.bootcamp.exchangeshop.models.Product;
import com.bootcamp.exchangeshop.models.RegistrationUser;
import com.bootcamp.exchangeshop.models.User;
import com.bootcamp.exchangeshop.repositories.UserRepository;

@Service
public class UserService {

		private final UserRepository userRepo;
		private final AddressService addService;

		public UserService(UserRepository userRepo, AddressService addService) {
			this.userRepo = userRepo;
			this.addService = addService;
		}
		
//		public User register(User newUser, BindingResult result) {
//			
//			Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
//			if(optionalUser.isPresent()) {
//				result.rejectValue("email", "unique", "This email is registered");
//			}
//			if(!newUser.getPassword().equals(newUser.getConfirm())) {
//				result.rejectValue("confirm", "match", "The password does not match");
//			}
//			if(result.hasErrors()) {
//				return null;
//			}
//			
//			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
//			newUser.setPassword(hashed);
//			Address registeredUserAddress = addService.createAdd(newUser.getAddress());
//			User registeredUser = userRepo.save(newUser);
//			
//			return registeredUser;
//		}
public User register(RegistrationUser newRegister, BindingResult result) {
			
			Optional<User> optionalUser = userRepo.findByEmail(newRegister.getEmail());
			if(optionalUser.isPresent()) {
				result.rejectValue("email", "unique", "This email is registered");
			}
			if(!newRegister.getPassword().equals(newRegister.getConfirm())) {
				result.rejectValue("confirm", "match", "The password does not match");
			}
			if(result.hasErrors()) {
				return null;
			}
			
			// Store all the information as User, and save it in database
			User newUser = new User();
			newUser.setConfirm(newRegister.getConfirm());
			newUser.setEmail(newRegister.getEmail());
			newUser.setUserName(newRegister.getUserName());
			newUser.setPassword(newRegister.getPassword());
			newUser.setWillProvideFeedback(newRegister.getWillProvideFeedback());
			System.out.println(newUser.getWillProvideFeedback());
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);

			User registeredUser = userRepo.save(newUser);
			
			// Store all the information as Address, with User attached, and save it in database
			Address newAddress = new Address();
			newAddress.setCity(newRegister.getCity());
			newAddress.setHousenumber(newRegister.getHousenumber());
			newAddress.setState(newRegister.getState());
			newAddress.setZipcode(newRegister.getZipcode());
			newAddress.setStreetname(newRegister.getStreetname());
			newAddress.setResident(registeredUser);
			Address registeredUserAddress = addService.createAdd(newAddress);
			return registeredUser;
			}

		public User login(LoginUser newLogin, BindingResult result) {
			Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
			if(!potentialUser.isPresent()) {
				result.rejectValue("email","unique", "This email is not registered");
				return null;
			}
			
			User loggedUser = potentialUser.get();
			if(!BCrypt.checkpw(newLogin.getPassword(), loggedUser.getPassword())){
				result.rejectValue("password","matches", "Invalid Password!");
			}
			
			if(result.hasErrors()) {
				return null;
			}
			return loggedUser;
		}
		// FIND ALL
		public List<User> allUsers(){
			return userRepo.findAll();
		}
		
		//CREATE
		public User createUser(User newUser) {
			return userRepo.save(newUser);
		}
		
		// FIND ONE
		public User oneUser(Long id) {
			Optional<User> optionalUser = userRepo.findById(id);
			if(optionalUser.isPresent()){
				return optionalUser.get();
			}
			else {
				return null;
			}
		}
		
		// UPDATE
		public User updateRegUser(RegistrationUser oneRegister, User oneUser, Address oneAddress) {
			
			oneUser.setConfirm(oneRegister.getConfirm());
//			oneUser.setEmail(oneRegister.getEmail());
			oneUser.setUserName(oneRegister.getUserName());
//			oneUser.setPassword(newRegister.getPassword());
			oneUser.setWillProvideFeedback(oneRegister.getWillProvideFeedback());
//			System.out.println(newUser.getWillProvideFeedback());
//			String hashed = BCrypt.hashpw(oneUser.getPassword(), BCrypt.gensalt());
//			newUser.setPassword(hashed);

			User updatedUser = userRepo.save(oneUser);
			
			// Store all the information as Address, with User attached, and save it in database
//			Address newAddress = new Address();
			oneAddress.setCity(oneRegister.getCity());
			oneAddress.setHousenumber(oneRegister.getHousenumber());
			oneAddress.setState(oneRegister.getState());
			oneAddress.setZipcode(oneRegister.getZipcode());
			oneAddress.setStreetname(oneRegister.getStreetname());
			oneAddress.setResident(updatedUser);
			Address updatedUserAddress = addService.updateAdd(oneAddress);
			return updatedUser;

		}
		
}
