package org.cc.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.cc.security.constants.TemplateConstants;
import org.cc.security.constants.UriConstants;
import org.cc.security.entity.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(UriConstants.USER_REGISTER)
public class UserRegistrationController extends AbstractController{
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	

	
    @GetMapping()
    public ModelAndView regGet(HttpServletRequest request, @ModelAttribute("userProfile") UserProfile form, Model model) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.USER_REGISTER_PAGE);
        return mav;
    }
    
    @PostMapping()
    public ModelAndView regPost(@ModelAttribute("userProfile") UserProfile form, BindingResult binding, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.USER_REGISTER_PAGE);
//    	LOGGER.info("Data: "+new ObjectMapper().valueToTree(form));
    	mav.addObject("userProfile", form);
    	try {
			if(!form.getPassword().equals(form.getConfirmPassword())) {
				return mav.addObject("error", "Passwords mismatch");
			}
			UserProfile user = getUserProfileService().findByUserName(form.getUserName());
			if(!StringUtils.isEmpty(user)) {
				return mav.addObject("error", "User Already Exists");
			}
			//set the Default role to user
			form.setRole("ROLE_USER");
			getUserProfileService().saveUserProfile(form);
		} catch (Exception e) {
			LOGGER.error("Error: "+e.getMessage());
			return mav.addObject("error", "User Creation failed");
		}
    	mav.addAllObjects(getPopup("success", "success", null, "User Created Successfully.", UriConstants.LOGIN));
    	
        return mav;
    }

    
}
