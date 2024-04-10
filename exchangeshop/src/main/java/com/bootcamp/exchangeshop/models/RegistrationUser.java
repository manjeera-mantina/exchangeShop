package com.bootcamp.exchangeshop.models;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationUser {
	    
	    @NotEmpty(message="Username is required!")
	    @Size(min=3, max=30, message="Username must be atleast 3 characters")
	    private String userName;
	    
	    @NotEmpty(message="Email is required!")
	    @Email(message="Please enter a valid email!")
	    private String email;
	    
	    @NotEmpty(message="Password is required!")
	    @Size(min=8, max=128, message="Password must be atleast 8 characters")
	    private String password;
	    
	    @Transient
	    @NotEmpty(message="Confirm Password is required!")
	    @Size(min=8, max=128, message="Confirm Password must be atleast 8 characters")
	    private String confirm;
	    
	    @NotEmpty(message="House number is required!")
	    @Size(min=1, max=30, message="House number must be atleast 1 character")
	    private String housenumber;
	    
	    @NotEmpty(message="Street name is required!")
	    @Size(min=3, max=30, message="Street name must be atleast 3 characters")
	    private String streetname;
	    
	    @NotEmpty(message="City name is required!")
	    @Size(min=3, max=20, message="City name must be atleast 3 characters")
	    private String city;
	    
	    @NotEmpty(message="State name is required!")
	    @Size(min=2, max=20, message="State name must be atleast 2 characters")
	    private String state;
	    
	    @NotNull(message="Zip code is required!")
	    @Min(value=10000, message="Zip code must be 5 characters")
	    @Max(value=99999, message="Zip code must be 5 characters")
	    private Integer zipcode;
	    
	    @NotNull(message="Answer is required!")
	    private Boolean willProvideFeedback;

	    public RegistrationUser () {}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirm() {
			return confirm;
		}

		public void setConfirm(String confirm) {
			this.confirm = confirm;
		}

		public String getHousenumber() {
			return housenumber;
		}

		public void setHousenumber(String housenumber) {
			this.housenumber = housenumber;
		}

		public Boolean getWillProvideFeedback() {
			return willProvideFeedback;
		}

		public void setWillProvideFeedback(Boolean willProvideFeedback) {
			this.willProvideFeedback = willProvideFeedback;
		}

		public String getStreetname() {
			return streetname;
		}

		public void setStreetname(String streetname) {
			this.streetname = streetname;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public Integer getZipcode() {
			return zipcode;
		}

		public void setZipcode(Integer zipcode) {
			this.zipcode = zipcode;
		}
	    
	    
}
