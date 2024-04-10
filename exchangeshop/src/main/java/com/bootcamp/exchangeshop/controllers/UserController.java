package com.bootcamp.exchangeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bootcamp.exchangeshop.models.LoginUser;
import com.bootcamp.exchangeshop.models.RegistrationUser;
import com.bootcamp.exchangeshop.models.User;
import com.bootcamp.exchangeshop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
			
			private final UserService userService;
					
			public UserController(UserService userService) {
				this.userService = userService;
			}
			
			    @GetMapping("/")
			    public String renderLogRegForm(Model model) {
			    
			        // Bind empty User and LoginUser objects to the JSP
			        // to capture the form input
			        model.addAttribute("newRegister", new RegistrationUser());
			        model.addAttribute("newLogin", new LoginUser());
			        return "logreg.jsp";
			    }
			    
			    @PostMapping("/register")
			    public String processRegister(@Valid @ModelAttribute("newRegister") RegistrationUser newRegister, 
			            BindingResult result, Model model, HttpSession session) {
			        
			        // call the register method in the service 
			        // to do some extra validations and create a new user!
			    	User registeredUser = userService.register(newRegister,  result);
			        
			        if(result.hasErrors()) {
			            // sending the empty LoginUser before 
			            // re-rendering the page.
			            model.addAttribute("newLogin", new LoginUser());
			            return "logreg.jsp";
			        }
			        else {
				        // No errors! 
			        	// Store their ID from the DB in session, 
				        // in other words, log them in.
//			        	System.out.println("before the session");
			        	session.setAttribute("userId", registeredUser.getId());	        // No errors! 
			        	session.setAttribute("userName", registeredUser.getUserName());	      
//			        	System.out.println("after the session");
			        	// split the full name of the user and pass the first name for rendering
			        	String[] usrname = (registeredUser.getUserName()).split(" ");
			        	session.setAttribute("firstname", usrname[0]);	      


			        	return "redirect:/dashboard";
			        }
			    }
			    
			    @PostMapping("/login")
			    public String processLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			            BindingResult result, Model model, HttpSession session) {
			        
			    	// call the login method in the service 
			        // to do some extra validations and login the user!
			        User LoggedUser = userService.login(newLogin, result);
			    
			        if(result.hasErrors()) {
			            model.addAttribute("newRegister", new RegistrationUser());
			            return "logreg.jsp";
			        }
			        else {
			        	 // No errors! 
				        // Store their ID from the DB in session, 
				        // in other words, log them in.
			        	session.setAttribute("userId", LoggedUser.getId());
			        	session.setAttribute("userName", LoggedUser.getUserName());
			        	// split the full name of the user and pass the first name for rendering
			        	String[] usrname = (LoggedUser.getUserName()).split(" ");
			        	session.setAttribute("firstname", usrname[0]);	      

			        return "redirect:/dashboard";
			        }
			    
			}
		@GetMapping("/logout")
		public String clearSession(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}

}
