package com.hcl.ingMortgage.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	UserService userService;
	@InjectMocks
	UserController userController;
	
	@Test
	public void testGetUser() {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setAddress("Bangalore");
		userResponseDto.setContact(7657646854L);
		userResponseDto.setDob(LocalDate.now());
		userResponseDto.setEmail("manisha@gmail.com");
		userResponseDto.setUserId(1);
		userResponseDto.setUserName("manisha");
		Mockito.when(userService.fetchUserDetails(Mockito.anyInt())).thenReturn(userResponseDto);
		ResponseEntity<UserResponseDto> actual= userController.getUser(1);
		assertNotNull(actual);
	}

}
