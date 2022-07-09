package org.cc.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cc.security.constants.TemplateConstants;
import org.cc.security.constants.UriConstants;
import org.cc.security.entity.CreditCardDtl;
import org.cc.security.entity.UserProfile;
import org.cc.security.model.CreditCardDtlDto;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(UriConstants.CARDS)
public class CardManagementController extends AbstractController{
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	

	//card List
    @GetMapping
    public ModelAndView cardList(HttpServletRequest request, @ModelAttribute("creditCardDtl") CreditCardDtl form, Model model) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_LIST_PAGE);
    	if(!isAdmin())form.setCreateId(getCurrentUserName());
    	List<CreditCardDtl> cards = getCreditCardDtlService().findBySearchCriteria(form);
    	mav.addObject("cards", cards);
        return mav;
    }
    
    @PostMapping
    public ModelAndView regPost(@ModelAttribute("creditCardDtl") CreditCardDtl form, BindingResult binding, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_LIST_PAGE);
    	if(!isAdmin())form.setCreateId(getCurrentUserName());
    	List<CreditCardDtl> cards = getCreditCardDtlService().findBySearchCriteria(form);
    	mav.addObject("cards", cards);
        return mav;
    }

    
    @GetMapping(UriConstants.CARDS_CREATE)
    public ModelAndView cardCreateGet(HttpServletRequest request, @ModelAttribute("creditCardDtl") CreditCardDtlDto form, Model model) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_CREATE_PAGE);
    	mav.addObject(TemplateConstants.PAGE_SCRIPT, "cardCreate");
    	mav.addObject("createupdate", "create");
        return mav;
    }
    
    @PostMapping(UriConstants.CARDS_CREATE)
    public ModelAndView cardCreatePost(@ModelAttribute("creditCardDtl") CreditCardDtlDto form, BindingResult binding, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_CREATE_PAGE);
    	mav.addObject(TemplateConstants.PAGE_SCRIPT, "cardCreate");
    	LOGGER.info("Data: "+new ObjectMapper().valueToTree(form));
    	try {
    		
    		CreditCardDtl cardDtl = getCreditCardDtlService().findByCardNumber(form.getCcNumber());
    		if(!StringUtils.isEmpty(cardDtl)) {
				return mav.addObject("error", "Card Already Exists");
			}
    		CreditCardDtl crediCardDtl = transferToObject(form, CreditCardDtl.class);
    		crediCardDtl.setCreateId(getCurrentUserName());
    		crediCardDtl.setUpdateId(getCurrentUserName());
    		LOGGER.info("crediCardDtl: "+new ObjectMapper().valueToTree(crediCardDtl));
    		getCreditCardDtlService().saveCreditCardDtl(crediCardDtl);
		} catch (Exception e) {
			LOGGER.error("Error: "+e.getMessage());
			return mav.addObject("error", "Card Creation failed");
		}
    	mav.addAllObjects(getPopup("success", "success", null, "Card Created Successfully.", UriConstants.CARDS));
    	mav.addObject("createupdate", "create");
        return mav;
    }
    
    @GetMapping(UriConstants.CARDS_EDIT)
    public ModelAndView cardEditGet(HttpServletRequest request, @ModelAttribute("creditCardDtl") CreditCardDtlDto form, Model model) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_CREATE_PAGE);
    	LOGGER.info("cardPostGet: "+form.getId());
    	mav.addObject(TemplateConstants.PAGE_SCRIPT, "cardCreate");
    	
    	try {
			CreditCardDtl obj = getCreditCardDtlService().findOne(form.getId());
			if(!isAdmin() && !obj.getCreateId().equalsIgnoreCase(getCurrentUserName())) {
				return mav.addAllObjects(getPopup("error", "error", null, 
						"User Not Authorised to Update This Card details", UriConstants.CARDS));
			}
			form = transferToObject(obj, CreditCardDtlDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	mav.addObject("creditCardDtl", form);
    	mav.addObject("createupdate", "update");
        return mav;
    }
    
    @PostMapping(UriConstants.CARDS_EDIT)
    public ModelAndView cardPostGet(HttpServletRequest request, @ModelAttribute("creditCardDtl") CreditCardDtlDto form, Model model) {
    	ModelAndView mav = new ModelAndView(TemplateConstants.CARD_CREATE_PAGE);
    	mav.addObject(TemplateConstants.PAGE_SCRIPT, "cardCreate");
    	LOGGER.info("cardPostGet");
    	try {
    		
    		CreditCardDtl cardDtl = getCreditCardDtlService().findOne(form.getId());
    		if(StringUtils.isEmpty(cardDtl)) {
				return mav.addObject("error", "Card Not Exists");
			}
    		CreditCardDtl cardUpdatedDtls = transferToObject(form, CreditCardDtl.class);
    		
    		cardDtl.setCcExpiry(cardUpdatedDtls.getCcExpiry());
    		cardDtl.setUpdateId(getCurrentUserName());
    		LOGGER.info("cardUpdatedDtls: "+new ObjectMapper().valueToTree(cardDtl));
    		getCreditCardDtlService().saveCreditCardDtl(cardDtl);
		} catch (Exception e) {
			LOGGER.error("Error: "+e.getMessage());
			return mav.addObject("error", "Card Update failed");
		}
    	mav.addAllObjects(getPopup("success", "success", null, "Card Updated Successfully.", UriConstants.CARDS));
    	mav.addObject("createupdate", "update");
        return mav;
    }

    
}
