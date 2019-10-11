package com.hcl.ingMortgage.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.controller.MortgageController;
import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.entity.User;
import com.hcl.ingMortgage.exception.InvalidUserException;
import com.hcl.ingMortgage.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(MortgageController.class);

	
	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 * @param userId -NotNull
	 * @return UserResponseDto- user details related to that userId
	 */
	@Override
	public UserResponseDto fetchUserDetails(Integer userId) {
		logger.info(":: Enter into UserService--------::fetchUserDetails()");
		Optional<User> userDetails = userRepository.findById(userId);
		UserResponseDto userResponseDto = null;
		User user = null;
		if(userDetails.isPresent()) {
			user=userDetails.get();
			userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, userResponseDto);
		}
		else { 
			throw new InvalidUserException("User is not present");
		}
				return userResponseDto;
	
	}

}
