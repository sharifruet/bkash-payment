package com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i2gether.beximcom.bkashpayment.bkashpayment.exception.AlreadyExistsException;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity.Payment;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.entity.PaymentBody;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.model.Message;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.service.PaymentService;
import com.i2gether.beximcom.bkashpayment.bkashpayment.webhook.util.WebhookUtility;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by alam.ashraful on 6/3/2018.
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private static final Logger LOG = LoggerFactory.getLogger(WebhookController.class);
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> consumeMessage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  {
    	
    	try {

	        String messagetype = httpServletRequest.getHeader("x-amz-sns-message-type");
	        LOG.info("BkashPgwWebhookController:: consumeMessage:: Header:: messagetype :" + messagetype);
	
	        LOG.info("BkashPgwWebhookController:: consumeMessage:: body:: " + httpServletRequest.getInputStream());
	        Scanner scan = new Scanner(httpServletRequest.getInputStream());
	        StringBuilder builder = new StringBuilder();
	        while (scan.hasNextLine()) {
	            builder.append(scan.nextLine());
	        }
	        scan.close();
	        LOG.info("BkashPgwWebhookController:: consumeMessage:: body:: " + builder.toString());
	
	        Message msg = new ObjectMapper().readValue(builder.toString(), Message.class);
	        
	        LOG.info("BkashPgwWebhookController:: consumeMessage:: msg:: " + msg.getSignatureVersion());
	/*
	        if (msg.getSignatureVersion().equals("1")) {
	            LOG.info("BkashPgwWebhookController:: Signature verification started....");
	            if (WebhookUtility.isMessageSignatureValid(msg)) {
	                LOG.info("BkashPgwWebhookController:: Signature verification succeeded");
	            } else {
	                LOG.info("BkashPgwWebhookController:: Signature verification failed");
	                throw new SecurityException("Signature verification failed.");
	            }
	        } else {
	            LOG.info("BkashPgwWebhookController:: Unexpected signature version. Unable to verify signature.");
	        }
	*/
	        if (msg.getType().equals("Notification")) {
	            LOG.info("BkashPgwWebhookController:: Notification.");
	            /*
	            String logMsgAndSubject = "";
	            if (msg.getSubject() != null) {
	                logMsgAndSubject += " Subject: " + msg.getSubject();
	            }
	            logMsgAndSubject += "Message: " + msg.getMessage();
	            LOG.info("BkashPgwWebhookController:: "+logMsgAndSubject);*/
	            paymentService.add(convertToEntity(msg));
	        } else if (msg.getType().equals("SubscriptionConfirmation")) {
	            LOG.info("BkashPgwWebhookController:: SubscriptionConfirmation.");
	            Scanner sc = new Scanner(new URL(msg.getSubscribeURL()).openStream());
	            StringBuilder sb = new StringBuilder();
	            while (sc.hasNextLine()) {
	                sb.append(sc.nextLine());
	            }
	            LOG.info("BkashPgwWebhookController:: Subscription confirmation (" + msg.getSubscribeURL() + ") Return value: " + sb.toString());
	        } else if (messagetype.equals("UnsubscribeConfirmation")) {
	            LOG.info("BkashPgwWebhookController:: UnsubscribeConfirmation.");
	            LOG.info("BkashPgwWebhookController:: Unsubscribe confirmation: " + msg.getMessage());
	        } else {
	            LOG.info("BkashPgwWebhookController:: Unknown message type.");
	        }
	        LOG.info("BkashPgwWebhookController:: Done processing message: " + msg.getMessageId());
	        
	        	return new ResponseEntity<String>("Processed successfully", HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<String>("Processing error occurred", HttpStatus.OK);
		}
    }

	private Payment convertToEntity(Message msg) throws JsonMappingException, JsonProcessingException {
		Payment payment = modelMapper.map(msg, Payment.class);
		String bodyContent = msg.getMessage().toString();
		PaymentBody body = new ObjectMapper().readValue(bodyContent, PaymentBody.class);
		payment.setPaymentBody(body);
		return payment;
	}
}
