package com.hcl.ingMortgage.service;

import java.util.Optional;

import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.entity.User;

public interface UserService {
	
	public UserResponseDto fetchUserDetails(Integer userId);

}
