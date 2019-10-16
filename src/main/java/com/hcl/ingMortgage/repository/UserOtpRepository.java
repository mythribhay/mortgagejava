package com.hcl.ingMortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ingMortgage.entity.UserOtp;
import com.warrenstrange.googleauth.ICredentialRepository;



public interface UserOtpRepository extends JpaRepository<UserOtp, String>,ICredentialRepository {

}
