package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class PaymentBody {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String dateTime;
    
	private String debitMSISDN;
    
	private String creditOrganizationName;
    
	private String creditShortCode;
    
	private String trxID;
    
	private String transactionStatus;
    
	private String transactionType;
    
	private double amount;
    
	private String currency;
    
	private String transactionReference;
	
    @OneToOne(mappedBy = "paymentBody")
    private Payment payment;
	
}
