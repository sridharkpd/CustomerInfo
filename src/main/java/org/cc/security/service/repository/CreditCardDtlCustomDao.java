package org.cc.security.service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cc.security.entity.CreditCardDtl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class CreditCardDtlCustomDao {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	 
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<CreditCardDtl> findBySearchCriteria(CreditCardDtl creditCardDtl){
		StringBuilder sb = new StringBuilder("select r ");
		sb.append(" from CreditCardDtl r ");
		sb.append(" where 1=1 ");
		
		if (StringUtils.hasText(creditCardDtl.getCcNumber()))
			sb.append(" and r.ccNumber LIKE :ccNumber");
		
		if (StringUtils.hasText(creditCardDtl.getCreateId()))
			sb.append(" and r.createId = :createId");
		
		Query query = entityManager.createQuery(sb.toString());
		
		if (StringUtils.hasText(creditCardDtl.getCcNumber()))
			query.setParameter("ccNumber", '%' + creditCardDtl.getCcNumber()+ '%');
		
		if (StringUtils.hasText(creditCardDtl.getCreateId()))
			query.setParameter("createId", creditCardDtl.getCreateId());
				
		return query.getResultList();
	}


}
