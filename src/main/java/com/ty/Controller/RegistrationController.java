package com.ty.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.Publishevent.RegistrationCompleteEvent;
import com.ty.Service.UserService;
import com.ty.entity.User;
import com.ty.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/user")
public class RegistrationController {
	@Autowired
	private UserService userService;

	private ApplicationEventPublisher publisher;

	@PostMapping("/register")
	public String userRegistertion(@RequestBody UserModel userModel, final HttpServletRequest request) {
		User user = userService.saveUserRegister(userModel);
		// send email to user--to activate message
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrlMtd(request)));

		return "user '" + user.getName() + "' saved successfully in database";

	}

	@GetMapping("/hello")
	public String view() {
		return "this is a secured website";

	}

	private String applicationUrlMtd(HttpServletRequest request) {

		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
