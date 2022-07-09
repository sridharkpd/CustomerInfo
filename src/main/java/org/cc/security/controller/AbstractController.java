package org.cc.security.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cc.security.service.CreditCardDtlService;
import org.cc.security.service.CustomUser;
import org.cc.security.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class AbstractController {
	
	@Autowired
    private UserProfileService userProfileService;
	
	@Autowired
    private CreditCardDtlService creditCardDtlService;
	
	public UserProfileService getUserProfileService() {
		return userProfileService;
	}
	
	public CreditCardDtlService getCreditCardDtlService() {
		return creditCardDtlService;
	}
	
	public String getCurrentUserName() {
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}
	
	public boolean isAdmin() {
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null && user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return true;
		}
		return false;
	}
	
	public static <A> A transferToObject(Object source, Class<A> clazz) {
		ObjectMapper objectMapper = objectMapper();
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(source);
		} catch (NoClassDefFoundError e) {
			objectMapper = objectMapper();
			try {
				jsonStr = objectMapper.writeValueAsString(source);
			} catch (JsonProcessingException e1) {
				// DO NOTHING
			}
		} catch (JsonProcessingException e) {
			// DO NOTHING
		}
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_DEFAULT);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configOverride(Map.class).setInclude(Value.construct(Include.NON_NULL, Include.NON_NULL));

		return mapper;
	}
	
	public static Map<String, Object> getPopup(String id, String type, String title, Object content,
			String redirectLink) {
		Map<String, Object> map = new HashMap<>();
		map.put("mainPopupId", id);
		map.put("mainMessageType", type);
		if (!StringUtils.isEmpty(title)) {
			map.put("mainPopupTitle", title);
		}
		if (!StringUtils.isEmpty(redirectLink)) {
			map.put("redirectLink", redirectLink);
		}
		map.put("mainMessage", content);
		return map;
	}

}
