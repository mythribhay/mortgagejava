package com.hcl.ingMortgage.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ingMortgage.entity.Mortgage;

@Repository
public interface MortgageRepository extends JpaRepository<Mortgage, Integer>{
	
	public Optional<List<Mortgage>> findByEmiDate(Integer emiDate);

}
