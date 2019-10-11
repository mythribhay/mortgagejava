package com.hcl.ingMortgage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.dto.EmiDeductResponse;
import com.hcl.ingMortgage.dto.UpdateMortgageDto;
import com.hcl.ingMortgage.dto.UpdateMortgageResponse;
import com.hcl.ingMortgage.entity.Mortgage;
import com.hcl.ingMortgage.repository.MortgageRepository;

@Service
public class MortgageServiceSheduldeImpl implements MortgageServiceShedulde {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	MortgageRepository mortgageRepository;
	
	/**
	 * 
	 * @param no arg
	 * @return EmiDeductResponse 
	 */

	@Override
	public EmiDeductResponse payEmi() {
		logger.info(":: Enter into LoginController--------::login()");
		LocalDate currentDate = LocalDate.now();
		Integer emiDate = currentDate.getDayOfMonth();
		EmiDeductResponse emiDeductResponse = new EmiDeductResponse();
		Optional<List<Mortgage>> listMortgagesO = mortgageRepository.findByEmiDate(emiDate);

		if (listMortgagesO.isPresent()) {
			List<Mortgage> listMortgages = listMortgagesO.get();
			listMortgages.forEach(mortgage -> {
				if (mortgage.getMortgageOutstanding() > 0) {
					Double emi = mortgage.getEmiAmount();
					mortgage.setMortgageRepaid(mortgage.getMortgageRepaid() + emi);
					mortgage.setMortgageOutstanding(mortgage.getMortgageOutstanding() - emi);
					mortgage.setTenure(mortgage.getTenure() - 1);
					mortgageRepository.save(mortgage);
				}

			});
			emiDeductResponse.setMessage("emi deducted");
			emiDeductResponse.setStatusCode(201);

		} else {
			emiDeductResponse.setMessage("No emi deducted");
			emiDeductResponse.setStatusCode(401);
		}

		return null;
	}

	@Override
	public UpdateMortgageResponse updateMortgage(UpdateMortgageDto updateRequest) {
		logger.info(":: Enter into LoginController--------::login()");
		Mortgage mortgage = mortgageRepository.findById(updateRequest.getMortgageId()).get();
		mortgage.setEmiDate(updateRequest.getEmiDate());
		mortgage.setEmiAmount(updateRequest.getEmiAmount());
		mortgage.setTenure(updateRequest.getTenure());

		mortgageRepository.save(mortgage);
		UpdateMortgageResponse updateResponse = new UpdateMortgageResponse();
		updateResponse.setMessage("success");
		updateResponse.setStatusCode(200);

		return updateResponse;
	}

}
