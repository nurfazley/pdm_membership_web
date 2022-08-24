package com.pdm.membershipweb.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.pdm.membershipweb.helper.MyFirebaseMessagingHelper;
import com.pdm.membershipweb.model.AppNotification;


@Service("firebaseMessagingService")
@Transactional
public class FirebaseMessagingService {
	
	@Autowired
	private MyFirebaseMessagingHelper myFirebaseMessagingHelper;

	
	public String sendNotification(AppNotification appNotification, String token) {
        Notification notification = Notification
                .builder()
                .setTitle(appNotification.getTitle())
                .setBody(appNotification.getBodyContent())
                .build();
        
        Map<String, String> mapData = new HashMap<>();
        mapData.put("title", appNotification.getTitle());
        mapData.put("content", appNotification.getBodyContent());
        mapData.put("click_action", "FLUTTER_NOTIFICATION_CLICK");
        mapData.put("priority", "high");
        
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(mapData)
                .build();
        
        FirebaseMessaging fm = null;
        String messageId = null;
        
        try {
			fm = myFirebaseMessagingHelper.getFirebaseMessaging();
			messageId = fm.send(message);
		} 
        catch (IOException e) {
			e.printStackTrace();
		} 
        catch (FirebaseMessagingException e) {
			e.printStackTrace();
		}

        return messageId;
    }
	
	public BatchResponse sendMulticastNotification(AppNotification appNotification, List<String> tokenList) {
		Notification notification = Notification
                .builder()
                .setTitle(appNotification.getTitle())
                .setBody(appNotification.getBodyContent())
                .build();

		MulticastMessage message = MulticastMessage.builder()
	    .setNotification(notification)
	    //.putData("payload1", "data1")
	    //.putData("payload2", "data2")
	    .addAllTokens(tokenList)
	    .build();
		
		FirebaseMessaging fm = null;
		BatchResponse br = null;
        
        try {
			fm = myFirebaseMessagingHelper.getFirebaseMessaging();
			br = fm.sendMulticast(message);
		} 
        catch (IOException e) {
			e.printStackTrace();
		} 
        catch (FirebaseMessagingException e) {
			e.printStackTrace();
		}

        return br;
	}
}
