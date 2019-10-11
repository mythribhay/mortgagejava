package com.hcl.ingMortgage.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Mortgage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mortgageId;
	private Integer userId;
	private Double mortgageAmount;
	private Double interestRate;
	private Double mortgageRepaid;
	private Double mortgageOutstanding;
	private Double emiAmount;
	private Integer emiDate;
	private LocalDate emiStartDate;
	private LocalDate emiEndDate;
	private Integer tenure;
}
