package com.ty.Service;

import com.ty.entity.User;
import com.ty.model.UserModel;

public interface UserService {

	User saveUserRegister(UserModel userModel);

	void saveVerificationToken(String token, User user);

}
