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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="purchases")
public class Purchase {
	
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;

		    @OneToOne(fetch=FetchType.LAZY)
		    @JoinColumn(name="product_id")
		    private Product solditem;
		   
		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name="user_id")
		    private User purchasor;
		    
		    private Date dateOfPurchase;
		    
		    @Transient
//		    @NotNull
//		    @Min(1)
//		    @Max(3)
		    private Integer prodexpectationmet;

		    @Transient
//		    @NotNull
//		    @Min(1)
//		    @Max(3)
		    private Integer prodtimelinemet;
		    
		    @Transient
		    @NotNull
		    @Min(1)
		    @Max(3)
		    private Integer prodresponsemet;

		    private Integer discountPercent;
		    
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
		    
		    public Purchase() {}  
		   
		    
		    //  getters and setters 

		    public Long getId() {
				return id;
			}
			
			public void setId(Long id) {
				this.id = id;
			}
			
			public Product getSolditem() {
				return solditem;
			}
			public void setSolditem(Product solditem) {
				this.solditem = solditem;
			}
			public User getPurchasor() {
				return purchasor;
			}
			public void setPurchasor(User purchasor) {
				this.purchasor = purchasor;
			}
			public Date getDateOfPurchase() {
				return dateOfPurchase;
			}
			public void setDateOfPurchase(Date dateOfPurchase) {
				this.dateOfPurchase = dateOfPurchase;
			}
			public Integer getProdexpectationmet() {
				return prodexpectationmet;
			}
			public void setProdexpectationmet(Integer prodexpectationmet) {
				this.prodexpectationmet = prodexpectationmet;
			}
			public Integer getProdtimelinemet() {
				return prodtimelinemet;
			}
			public void setProdtimelinemet(Integer prodtimelinemet) {
				this.prodtimelinemet = prodtimelinemet;
			}
			public Integer getProdresponsemet() {
				return prodresponsemet;
			}
			public void setProdresponsemet(Integer prodresponsemet) {
				this.prodresponsemet = prodresponsemet;
			}
			public Integer getDiscountPercent() {
				return discountPercent;
			}
			public void setDiscountPercent(Integer discountPercent) {
				this.discountPercent = discountPercent;
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
