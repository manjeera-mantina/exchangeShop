package com.bootcamp.exchangeshop.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="products")
public class Product {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotNull
	    @Size(min = 3, max = 20, message="Product name is required")
	    private String productname;
	    @NotNull
	    @Size(min = 5, max = 20, message="Product category is required")
	    private String productcategory;
	    @NotNull
	    @Min(2000)
	    private Integer year;
	    @NotNull
	    @Size(min = 2, max = 10, message="Product condition is required")
	    private String amtused;
	    @NotNull(message="Product price is required")
	    @Min(1)
	    private Long price;
	    
	    private Boolean isSold;

	   
	   
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User postor;
	    
	    @OneToOne(mappedBy="solditem", fetch=FetchType.LAZY)
	    private Purchase transaction;
	    
	    @Size(min = 1, max = 255, message="Product image URL is optional")
	    private String productImage;
	    
	    @NotNull
	    private Date dateOfPosting;
//
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	    // This will not allow the createdAt column to be updated after creation
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	    
	    public Product() {
	    }
	    
	    public Product(String productname, String productcategory, Integer year, String amtused, Long price, String productImage) {
	    	super();
	        this.productname = productname;
	        this.productcategory = productcategory;
	        this.year = year;
	        this.amtused = amtused;
	        this.price = price;
	        this.productImage = productImage;
	    }
	    
	    //  getters and setters 

	    public Long getId() {
			return id;
		}
	    public void setId(Long id) {
			this.id = id;
		}
		public String getProductImage() {
			return productImage;
		}
		public void setProductImage(String productImage) {
			this.productImage = productImage;
		}
		public Date getDateOfPosting() {
			return dateOfPosting;
		}
		public void setDateOfPosting(Date dateOfPosting) {
			this.dateOfPosting = dateOfPosting;
		}
		 public Boolean getIsSold() {
				return isSold;
			}
			public void setIsSold(Boolean isSold) {
				this.isSold = isSold;
			}
		 public Purchase getTransaction() {
				return transaction;
			}
			public void setTransaction(Purchase transaction) {
				this.transaction = transaction;
			}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getProductcategory() {
			return productcategory;
		}
		public void setProductcategory(String productcategory) {
			this.productcategory = productcategory;
		}
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}
		public String getAmtused() {
			return amtused;
		}
		public void setAmtused(String amtused) {
			this.amtused = amtused;
		}
		public Long getPrice() {
			return price;
		}
		public void setPrice(Long price) {
			this.price = price;
		}
		public User getPostor() {
			return postor;
		}
		public void setPostor(User postor) {
			this.postor = postor;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt() {
			onCreate();
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
		public void setUpdatedAt() {
			onUpdate();
		}
}
