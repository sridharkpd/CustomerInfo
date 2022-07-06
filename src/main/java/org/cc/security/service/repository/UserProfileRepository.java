package org.cc.security.service.repository;

import org.cc.security.entity.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserProfileRepository extends CrudRepository<UserProfile, Long>{
	
	@Query("select u from UserProfile u where u.userName = :userName ")
	UserProfile findByUserName(@Param("userName") String userName);

}
