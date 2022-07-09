package org.cc.security.service.impl;

import org.cc.security.entity.UserProfile;
import org.cc.security.service.UserProfileService;
import org.cc.security.service.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	 @Autowired
	 private UserProfileRepository userProfileRepository;
	 
	 private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public UserProfile saveUserProfile(UserProfile userProfile) {
		userProfile.setPassword(getPassword(userProfile.getPassword()));
		return userProfileRepository.save(userProfile);
	}
	
	@Override
	public UserProfile findByUserName(String userName) {
		return userProfileRepository.findByUserName(userName);
	}

	private String getPassword(String raw) {
        return passwordEncoder.encode(raw);
    }
    

}
