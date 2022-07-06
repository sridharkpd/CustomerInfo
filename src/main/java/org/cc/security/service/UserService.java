package org.cc.security.service;

import java.util.Collection;

import org.cc.security.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    private UserProfileService userProfileService;
	
	 private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomUser getUserByUsername(String username) {
        //CustomUser originUser = database.getDatabase().get(username);
    	UserProfile userProfile = userProfileService.findByUserName(username);
        
        if (userProfile == null) {
            return null;
        }

        return new CustomUser(userProfile.getId(), userProfile.getUserName(), userProfile.getPassword(), getGrants(userProfile.getRole()));
    }
    
    private Collection<GrantedAuthority> getGrants(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
    
    private String getPassword(String raw) {
        return passwordEncoder.encode(raw);
    }
    
    
    
}
