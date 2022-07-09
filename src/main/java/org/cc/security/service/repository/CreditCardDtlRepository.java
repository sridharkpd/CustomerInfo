package org.cc.security.service.repository;

import java.util.List;
import java.util.Optional;

import org.cc.security.entity.CreditCardDtl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  CreditCardDtlRepository extends CrudRepository<CreditCardDtl, Long>{
	
	@Query("select u from CreditCardDtl u where u.ccNumber like :ccNumber ")
	List<CreditCardDtl> findAllByCardNumber(@Param("ccNumber") String ccNumber);
	
	@Query("select u from CreditCardDtl u where u.ccNumber = :ccNumber ")
	CreditCardDtl findByCardNumber(@Param("ccNumber") String ccNumber);
	
	
}
