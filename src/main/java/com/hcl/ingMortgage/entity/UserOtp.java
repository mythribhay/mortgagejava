package com.hcl.ingMortgage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sharath vemperala
 *
 */
@Entity
@Table(name="userOtp")
public class UserOtp {
	
	@Id	
    @Column(name = "email")
	private String email;
	private String secretkey;
	private int validationcode;
	private long validity;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecretKey() {
		return secretkey;
	}

	public void setSecretKey(String secretkey) {
		this.secretkey = secretkey;
	}

	public int getValidationcode() {
		return validationcode;
	}

	public void setValidationcode(int validationcode) {
		this.validationcode = validationcode;
	}

	public void setValidity(long validity) {
		this.validity = validity;
	}

	public long getValidity() {
		return validity;
	}
}
