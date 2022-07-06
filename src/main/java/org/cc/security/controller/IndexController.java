package org.cc.security.controller;

import org.cc.security.constants.TemplateConstants;
import org.cc.security.constants.UriConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(UriConstants.HOME)
    public String index() {
        return TemplateConstants.INDEX;
    }

    @GetMapping(UriConstants.LOGIN)
    public String login() {
        return TemplateConstants.LOGIN;
    }

    @GetMapping(UriConstants.LOGOUT)
    public String logout() {
        return TemplateConstants.LOGOUT;
    }
}
