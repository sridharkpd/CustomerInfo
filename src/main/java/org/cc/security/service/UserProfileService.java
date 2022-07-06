package org.cc.security.service;

import org.cc.security.entity.UserProfile;

public interface  UserProfileService {
	
	// Save profile
	UserProfile saveUserProfile(UserProfile userProfile);
	
	UserProfile findByUserName(String userName);
 
   

}
