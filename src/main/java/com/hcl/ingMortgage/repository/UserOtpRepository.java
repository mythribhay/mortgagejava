package com.hcl.ingMortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ingMortgage.entity.UserOtp;



public interface UserOtpRepository extends JpaRepository<UserOtp, String> {

}
