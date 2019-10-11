package com.hcl.ingMortgage.schedulder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.ingMortgage.service.MortgageServiceShedulde;


@Component
public class ScheduledTasks {

	
	@Autowired
	MortgageServiceShedulde mortgageServiceShedulde;
	@Scheduled(fixedRate = 10000)
	public void scheduleTaskWithFixedRate() {
		System.out.println("break");
		mortgageServiceShedulde.payEmi();

	}

}
