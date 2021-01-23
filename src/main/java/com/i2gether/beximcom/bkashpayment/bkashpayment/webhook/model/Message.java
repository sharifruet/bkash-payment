package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by alam.ashraful on 6/3/2018.
 */

@Getter
@Setter
@ToString
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("MessageId")
    private String MessageId;
	
	@JsonProperty("Token")
    private String token;
	
	@JsonProperty("TopicArn")
    private String topicArn;
	
	@JsonProperty("Message")
    private JsonNode  message;
	
	@JsonProperty("SubscribeURL")
    private String subscribeURL;
	
	@JsonProperty("Timestamp")
    private String timestamp;
	
	@JsonProperty("SignatureVersion")
    private String signatureVersion;
	
	@JsonProperty("Signature")
    private String signature;
	
	@JsonProperty("SigningCertURL")
    private String signingCertURL;
	
	@JsonProperty("Subject")
    private String subject;
	
	@JsonProperty("UnsubscribeURL")
    private String unsubscribeURL;
}
