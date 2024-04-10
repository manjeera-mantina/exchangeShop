package com.bootcamp.exchangeshop.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.exchangeshop.models.Address;
import com.bootcamp.exchangeshop.models.Product;
import com.bootcamp.exchangeshop.models.Purchase;
import com.bootcamp.exchangeshop.models.RegistrationUser;
import com.bootcamp.exchangeshop.models.User;
import com.bootcamp.exchangeshop.services.AddressService;
import com.bootcamp.exchangeshop.services.ProductService;
import com.bootcamp.exchangeshop.services.PurchaseService;
import com.bootcamp.exchangeshop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProductController {

			private final UserService userService;
			private final ProductService prodService;
			private final PurchaseService purchService;
			private final AddressService addService;
			
			
			public ProductController(ProductService prodService, UserService userService, PurchaseService purchService, AddressService addService) {
				this.prodService = prodService;
				this.userService = userService;
				this.purchService = purchService;
				this.addService = addService;
			}
			
			@GetMapping("/dashboard")
			public String renderProductsDashboard(HttpSession session, Model model) {
				// securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				// populating the dashboard with all fans ( from different users)
//				model.addAttribute("fans", fanService.allFans() );
				// rendering the page for the user to choose what they want to do
				return "prodDashboard.jsp";
			}
			
			@PostMapping("/process")
			public String renderCategory(HttpSession session, Model model, @RequestParam(value="processchosen") String process,
				    @RequestParam(value="categorychosen") String category) {

				// securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				return "redirect:/products/" + category + '/' + process;
//				return "redirect:/products/";
			}

			

			@GetMapping("/products/{category}/{process}")
			public String renderTableOrForm(HttpSession session, @PathVariable("category") String category, 
					@PathVariable("process") String process, Model model) {
//				 securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				if(process.equals("purchase")) {
					// removing the session attribute that saved the product id that was being considered for purchase
					session.setAttribute("prodID", null);
					List<Product> productsInCategory = prodService.allProductsbyCategory(category);
					model.addAttribute("products", productsInCategory);
					String s1 = category.substring(0, 1).toUpperCase();
				    String categoryCapitalized = s1 + category.substring(1);
					model.addAttribute("category", categoryCapitalized);
					return "showProducts.jsp"; 
				}
				else if(process.equals("post")) {
					Product product = new Product();
					product.setProductcategory(category);
					model.addAttribute("newProd", product);
					return "addProd.jsp";
				}
				else return "prodDashboard.jsp";

			}
			// View Account Information of Purchasor before purchase
			@GetMapping("/purchases/account/{id}")
			public String renderAccount(HttpSession session, @PathVariable("id") Long purchProdId, Model model) {

				// securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				// Gather information of the purchasor, who is in session
				Long userid = Long.valueOf(session.getAttribute("userId").toString());
				User purchasorInfo = userService.oneUser(userid);
				model.addAttribute("purchasorinfo", purchasorInfo);
				// Add the product under consideration to session
				session.setAttribute("prodID",purchProdId);
				// Retrieving information of the product that is being considered for purchase
				Product purchasingInfo = prodService.oneProd(purchProdId);
				model.addAttribute("purchasingprodinfo", purchasingInfo);

				return "viewUser.jsp";
			}
			// Create Product Purchase, while updating the Product
			@GetMapping("/purchases/{id}")
			public String renderAddPurchForm(HttpSession session, @PathVariable("id") Long purchProdId, @ModelAttribute("newPurch") Purchase newPurch, Model model) {

				// securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				Long userID = Long.valueOf((session.getAttribute("userId")).toString());
				User userInfo = userService.oneUser(userID);
	    		model.addAttribute("userInfo", userInfo );
	    		model.addAttribute("prodId", purchProdId );
				model.addAttribute("newPurch", newPurch );
				return "addPurch.jsp";
			}
			// Complete Purchase Creation, taking input from form
		    @PostMapping("/purchases/confirm")
		    public String addPurchase(@Valid @ModelAttribute("newPurch") Purchase purchase, BindingResult result, HttpSession session, Model model) {
				
		    	// securing route against access without authentication

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		    	// 
		    	if (result.hasErrors()) {
		    	// If there are validation errors then render the page again
		    		System.out.println(result.getAllErrors());
		            return "addPurch.jsp";
		        } else {
		    		// when purchasor provides feedback include information in rating to seller 
//		            Integer presSellerRating = Math.round((purchase.getProdexpectationmet() + purchase.getProdtimelinemet() + purchase.getProdresponsemet())/3);
		        	
		        	// currently assign rating based on the query response input from purchasor
		        	Integer presSellerRating = purchase.getProdresponsemet();

		        	// check for already assigned rating and make the final rating
		            Integer prevSellerRating = purchase.getPurchasor().getSellerRating();
		            if(prevSellerRating!=null) presSellerRating = Math.round((prevSellerRating+presSellerRating)/2);
		            purchase.getPurchasor().setSellerRating(presSellerRating);
		            
		        	// the productId is saved in the rating parameters
		        	Long prodId = (long)purchase.getProdtimelinemet();

		            
		            // retrieving the information of the product to be purchased to set the isSold to true
		        	Product purchaseInfo = prodService.oneProd(prodId);
		        	purchaseInfo.setIsSold(true);
			
		            // If no errors Create a new Purchase and show in dashboard
		            Purchase newPurchase = purchService.createPurch(purchase);
		            String newPurchId = Long.toString(newPurchase.getId()); 
		            
		            model.addAttribute("newpurchase", newPurchase);
		            return "redirect:/purchases/confirm/" + newPurchId;
		        }
		    }
			@GetMapping("/purchases/confirm/{id}")
			public String renderConfirmForm(HttpSession session, @PathVariable("id") Long purchId, Model model) {

				// securing route against access without authentication
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}

				Purchase onePurchase = purchService.onePurch(purchId);
				model.addAttribute("onePurch", onePurchase );
				return "confirmPurchase.jsp";
			}
			// Create Product posting
		    @PostMapping("/products/new")
		    public String addProduct(@Valid @ModelAttribute("newProd") Product product, BindingResult result, HttpSession session) {
				
		    	// securing route against access without authentication

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		    	// 
		    	if (result.hasErrors()) {
		    	// If there are validation errors then render the page again
		    		System.out.println(result.getAllErrors());
		            return "addProd.jsp";
		        } else {
		        	// If no errors Create a new Product and show in dashboard
		            Product newProduct = prodService.createProd(product);
		            String newProdId = Long.toString(newProduct.getId());
		            	
		            return "redirect:/products/view/" + newProdId;
		        }
		    }
			// View Item
		    @GetMapping("/products/view/{id}")
			public String renderViewFanForm(HttpSession session, @PathVariable("id") Long newProdId, Model model) {

				// securing route against access without authentication
		    	
				if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
				
				// removing the session attribute that saved the product id that was being considered for purchase
				session.setAttribute("prodID", null);
				
				// Retrieve the details of the product to review
				Product oneProduct = prodService.oneProd(newProdId);
				
				// Add it to Model to be accessed in the jsp
				model.addAttribute("product", oneProduct );
				return "viewProd.jsp";
			}
			 // Show Editing User
		    @GetMapping("/users/edit/{id}")
		    public String renderEditUser(HttpSession session, @PathVariable("id") Long userid, Model model) {
				// securing route against access without authentication

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		    	// Retrieve the details of the product requested to be edited
		        User editingaccount = userService.oneUser(userid);
		        
				// Add it to Model to be pre-populated in the form 
		        model.addAttribute("editRegUser", new RegistrationUser());
		        model.addAttribute("editAcct",editingaccount);
		        return "editUser.jsp";
		    }
		    // Edit Item Updated
		    @PutMapping("/users/edit/{id}")
		    public String updateEditUser(HttpSession session, @Valid @ModelAttribute("editRegUser") RegistrationUser regEditedUser, BindingResult result,  @PathVariable("id") Long userid, Model model) {
				// securing route against access without authentication
	        	User editingAcct = userService.oneUser(userid);

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		        if (result.hasErrors()) {
			    	// If there are validation errors then render the page again
		    		System.out.println(result.getAllErrors());
			        model.addAttribute("editAcct",editingAcct);
		            return "editUser.jsp";
		        } else {
		        	// Update the product information with the edited data from form
		        	Address editingAdd = addService.oneAdd(userid);
		            User updatedUser = userService.updateRegUser(regEditedUser,editingAcct,editingAdd);
		            // Retrieving the category to set in route
//		            String category = updatedProd.getProductcategory();
//		            return "redirect:/dashboard";
		            // Getting the product id from the session for the user purchase
		            String productIdcons = (session.getAttribute("prodID")).toString();
					return "redirect:/purchases/account/" + productIdcons;

		        }
		    }
		 // Show Editing Item
		    @GetMapping("/products/edit/{id}")
		    public String renderEditProduct(HttpSession session, @PathVariable("id") Long prodId, Model model) {
				// securing route against access without authentication

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		    	// Retrieve the details of the product requested to be edited
		        Product editingproduct = prodService.oneProd(prodId);
				// Add it to Model to be pre-populated in the form 
		        model.addAttribute("editProd",editingproduct);
		        return "editProduct.jsp";
		    }
		    // Edit Item Updated
		    @PutMapping("/products/edit/{id}")
		    public String updateEditProduct(HttpSession session, @Valid @ModelAttribute("editProd") Product editedProd,BindingResult result,  @PathVariable("id") Long prodId) {
				// securing route against access without authentication

		    	if(session.getAttribute("userId")== null) {
					return "redirect:/";
				}
		        if (result.hasErrors()) {
			    	// If there are validation errors then render the page again
		    		System.out.println(result.getAllErrors());
		            return "editProduct.jsp";
		        } else {
		        	// Update the product information with the edited data from form
		            Product updatedProd = prodService.updateProd(editedProd);
		            // Retrieving the category to set in route
		            String category = updatedProd.getProductcategory();
//		            return "redirect:/dashboard";
					return "redirect:/products/" + category + '/' + "purchase";

		        }
		    }
		        // Delete Item
		        
		        @DeleteMapping("/products/delete/{id}")
		        public String removeFan(HttpSession session, @PathVariable("id") Long prodId){
					// securing route against access without authentication

		        	if(session.getAttribute("userId")== null) {
		    			return "redirect:/";
		    		}
			        // Retrieving the product to get its category to set in route
			        Product deletingproduct = prodService.oneProd(prodId);
		            String category = deletingproduct.getProductcategory();
		        	// Delete the product from database
		            prodService.deleteProd(prodId);
//		            return "redirect:/dashboard";
					return "redirect:/products/" + category + '/' + "purchase";

		        }
			}
