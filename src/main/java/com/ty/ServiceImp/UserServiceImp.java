package com.ty.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.Service.UserService;
import com.ty.entity.User;
import com.ty.entity.VerificationToken;
import com.ty.model.UserModel;
import com.ty.repository.UserRepository;
import com.ty.repository.VerficationTokenRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private VerficationTokenRepository verficationTokenRepository;

	@Override
	public User saveUserRegister(UserModel userModel) {
		User user = new User();
		user.setName(userModel.getName());
		user.setEmail(userModel.getEmail());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		userRepository.save(user);
		return user;
	}

	@Override
	public void saveVerificationToken(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(token, user);

		verficationTokenRepository.save(verificationToken);

	}

}
