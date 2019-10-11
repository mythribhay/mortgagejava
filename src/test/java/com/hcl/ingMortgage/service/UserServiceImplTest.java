package com.hcl.ingMortgage.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.entity.Mortgage;
import com.hcl.ingMortgage.entity.User;
import com.hcl.ingMortgage.exception.InvalidUserException;
import com.hcl.ingMortgage.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl UserServiceImpl;
	
	@Test
	public void testFetchUserDetails() {

		User user = new User();
		user.setAddress("bangalore");
		user.setContact(64543247699L);
		user.setDob(LocalDate.now());
		user.setEmail("manisha");
		user.setPassword("mani");
		user.setUserId(1);
		user.setUserName("Manisha");
		
		Optional<User> optionalUser=Optional.of(user);

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		if(optionalUser.isPresent()) {
			User eachUser=optionalUser.get();
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(eachUser, userResponseDto);
		}
		else { 
			throw new InvalidUserException("User is not present");
		}
				
		UserResponseDto actual =UserServiceImpl.fetchUserDetails(1);
		assertNotNull(actual);
		
	}
		
	}


