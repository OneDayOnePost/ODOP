package com.example.controller.MH;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MhHomeController {

    @Value("${google.clientId}") String clientId;
    @Value("${google.login.url}") String loginUrl;
    @Value("${google.redirect.url}") String redirectUrl;



    @GetMapping(value = "/mhlogin.do")
    public String loginGET() {

        return "/MH/login";

    }

    @GetMapping(value = "/mhtest.do")
    public String testGET() {

        return "/fragments/header";

    }

    @GetMapping(value = "/mhhome.do")
    public String homeGET() {
        return "/MH/Mhhome";
    }

    @GetMapping(value = "/mhtest1.do")
    public String test1GET() {
        return "/MH/Mhtest";
    }

    // 구글 로그인창 호출
    @GetMapping(value = "/getGoogleAuthUrl")
    public @ResponseBody String getGoogleAuthUrl(HttpServletRequest request) throws Exception {

        String reqUrl = clientId + "/o/oauth2/v2/auth?client_id=" + loginUrl + "&redirect_uri="
                + redirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        return reqUrl;
    }

}
