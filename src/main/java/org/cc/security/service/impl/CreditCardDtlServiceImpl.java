package org.cc.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.cc.security.entity.CreditCardDtl;
import org.cc.security.service.CreditCardDtlService;
import org.cc.security.service.repository.CreditCardDtlCustomDao;
import org.cc.security.service.repository.CreditCardDtlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardDtlServiceImpl implements CreditCardDtlService {
	
	 @Autowired
	 private CreditCardDtlRepository creditCardDtlRepository;
	 
	 @Autowired
	 private CreditCardDtlCustomDao creditCardDtlCustomDao;
	 

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public CreditCardDtl saveCreditCardDtl(CreditCardDtl creditCardDtl) {
		return creditCardDtlRepository.save(creditCardDtl);
	}
	
	@Override
	public List<CreditCardDtl> findBySearchCriteria(CreditCardDtl creditCardDtl) {
		return creditCardDtlCustomDao.findBySearchCriteria(creditCardDtl);
	}
	
	@Override
	public CreditCardDtl findByCardNumber(String ccNumber) {
		return creditCardDtlRepository.findByCardNumber(ccNumber);
	}

	public CreditCardDtl findOne(Long id) {
		Optional<CreditCardDtl> creditCardDtlOpt = creditCardDtlRepository.findById(id);
		if (creditCardDtlOpt.isPresent()) {
			return creditCardDtlOpt.get();
		} 
		return null;
	}
	
   

}
