package org.cc.security.service;

import java.util.List;

import org.cc.security.entity.CreditCardDtl;

public interface  CreditCardDtlService {
	
	// Save profile
	CreditCardDtl saveCreditCardDtl(CreditCardDtl crediCardDtl);
	
	List<CreditCardDtl> findBySearchCriteria(CreditCardDtl creditCardDtl);
	
	CreditCardDtl findByCardNumber(String ccNumber);
	
	CreditCardDtl  findOne(Long id);
 
   

}
