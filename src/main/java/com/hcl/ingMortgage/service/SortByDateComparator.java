package com.hcl.ingMortgage.service;

import java.util.Comparator;

import com.hcl.ingMortgage.dto.MortgageListResponseDto;

public class SortByDateComparator implements Comparator<MortgageListResponseDto> {

	@Override
	public int compare(MortgageListResponseDto obj1, MortgageListResponseDto obj2) {

		return obj1.getStartDate().compareTo(obj2.getStartDate());
	}

}
