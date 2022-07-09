package org.cc.security.controller;

import org.cc.security.constants.TemplateConstants;
import org.cc.security.constants.UriConstants;
import org.cc.security.security.IsAdmin;
import org.cc.security.security.IsUser;
import org.cc.security.service.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@IsUser
@Controller
@RequestMapping(UriConstants.USER)
public class UserController {

    @GetMapping(UriConstants.USER_HOME)
    public String home(Model model) {
        CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return TemplateConstants.CARD_LIST_PAGE;
    }

    @GetMapping(UriConstants.USER_ADMIN)
    @IsAdmin
    public String admin() {
        return TemplateConstants.USER_ADMIN_PAGE;
    }
}
