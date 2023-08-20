package com.ty.PublishListener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ty.Publishevent.RegistrationCompleteEvent;
import com.ty.Service.UserService;
import com.ty.entity.User;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
	@Autowired
	UserService service;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();

		service.saveVerificationToken(token, user);

		String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

	}

}
