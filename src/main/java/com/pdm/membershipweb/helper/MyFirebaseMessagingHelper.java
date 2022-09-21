package com.pdm.membershipweb.helper;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;


@Component
public class MyFirebaseMessagingHelper {
	
	private FirebaseApp app;
	
	
	public MyFirebaseMessagingHelper() {
		if (app == null) {
			init();
		}
	}
	
	public FirebaseMessaging getFirebaseMessaging() throws IOException {
		if (app == null) {
			init();
		}
		
	    return FirebaseMessaging.getInstance(app);
	}
	
	private void init() {
		GoogleCredentials googleCredentials = null;
		
		try {
			googleCredentials = GoogleCredentials
					.fromStream(new ClassPathResource("pdm-push-notification-firebase-adminsdk-tola4-1a3e47899e.json")
					.getInputStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	    FirebaseOptions firebaseOptions = FirebaseOptions
	            .builder()
	            .setCredentials(googleCredentials)
	            .build();
	    
	    app = FirebaseApp.initializeApp(firebaseOptions);
	    //app = FirebaseApp.initializeApp(firebaseOptions, "PDM Push Notification");
	}
}
