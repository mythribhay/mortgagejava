package com.hcl.ingMortgage.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private Integer userId;
	private String userName;
	private String email;
	private LocalDate dob;
	private Long contact;
	private String address;
	
}