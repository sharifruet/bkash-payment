package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String type;
	
    private String MessageId;
	
    private String token;

    private String topicArn;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentBodyId", referencedColumnName = "id")
	private PaymentBody paymentBody;
	
    private String subscribeURL;
	
    private String timestamp;
	
    private String signatureVersion;
	
    @Lob
    private String signature;

    private String signingCertURL;
	
    private String subject;
	
    private String unsubscribeURL;

}
