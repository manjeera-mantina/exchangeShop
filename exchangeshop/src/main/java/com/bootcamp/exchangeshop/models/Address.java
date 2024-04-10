package com.bootcamp.exchangeshop.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="address")
public class Address {

		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
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

		    @OneToOne(fetch=FetchType.LAZY)
		    @JoinColumn(name="user_id")
		    private User resident;
		    
		    @PrePersist
		    protected void onCreate(){
		        this.createdAt = new Date();
		    }
		    @PreUpdate
		    protected void onUpdate(){
		        this.updatedAt = new Date();
		    }
		    
		    @Column(updatable=false)
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date createdAt;
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date updatedAt;
		    
		    public Address() {
		        
		    }
		    
			public Address(String housenumber, String streetname, String city, String state, Integer zipcode) {
				super();
				this.housenumber = housenumber;
				this.streetname = streetname;
				this.city = city;
				this.state = state;
				this.zipcode = zipcode;
			}
			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}
		
			public String getHousenumber() {
				return housenumber;
			}
			public void setHousenumber(String housenumber) {
				this.housenumber = housenumber;
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
			public User getResident() {
				return resident;
			}
			public void setResident(User resident) {
				this.resident = resident;
			}
			public Date getCreatedAt() {
				return createdAt;
			}

			public void setCreatedAt(Date createdAt) {
				this.createdAt = createdAt;
			}

			public Date getUpdatedAt() {
				return updatedAt;
			}

			public void setUpdatedAt(Date updatedAt) {
				this.updatedAt = updatedAt;
			}
}
