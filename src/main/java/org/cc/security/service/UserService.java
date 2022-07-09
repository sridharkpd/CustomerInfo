package org.cc.security.service;

import java.util.Collection;

import org.cc.security.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    private UserProfileService userProfileService;
	

    public CustomUser getUserByUsername(String username) {
    	UserProfile userProfile = userProfileService.findByUserName(username);
        
        if (userProfile == null) {
            return null;
        }

        return new CustomUser(userProfile.getId(), userProfile.getUserName(), userProfile.getPassword(), getGrants(userProfile.getRole()));
    }
    
    private Collection<GrantedAuthority> getGrants(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
    
    
    
    
}
