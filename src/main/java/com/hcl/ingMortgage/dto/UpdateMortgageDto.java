package com.hcl.ingMortgage.dto;



public class UpdateMortgageDto {

	Integer mortgageId;
	Integer emiDate;
	Double emiAmount;
	Integer tenure;

	public Integer getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(Integer mortgageId) {
		this.mortgageId = mortgageId;
	}

	public Integer getEmiDate() {
		return emiDate;
	}

	public void setEmiDate(Integer emiDate) {
		this.emiDate = emiDate;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

}
