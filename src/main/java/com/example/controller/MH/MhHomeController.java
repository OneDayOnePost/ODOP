package com.example.controller.MH;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Member;
import com.example.repository.MH.MhMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MhHomeController {

    final String format = "MhHomeController => {}";

    final private MhMemberRepository mRepository;

    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    @Value("${spring.security.oauth2.client.registration.google.client-id}") String clientId;
    // @Value("${google.login.url}") String loginUrl;
    // @Value("${google.redirect.url}") String redirectUrl;

    @GetMapping(value = "/mhlogin.do")
    public String loginGET() {

        // log.info(format, clientId);

        return "/MH/login";

    }

    @GetMapping(value = "/alert.do")
    public String alertGET(HttpSession httpSession, Model model) {

        String alertMessage = (String)httpSession.getAttribute("alertMessage");

        // log.info(format, alertMessage);

        model.addAttribute("alertMessage", alertMessage);
        model.addAttribute("alertTitle", httpSession.getAttribute("alertTitle"));
        model.addAttribute("alertUrl", httpSession.getAttribute("alertUrl"));

        return "/alert";
    }

    @GetMapping(value = "/mhtest.do")
    public String testGET() {

        return "/fragments/header";

    }

    @GetMapping(value = "/mhhome.do")
    public String homeGET(@AuthenticationPrincipal User user) {

        if(user != null) {

            log.info(format, user.toString());

        }

        return "/MH/Mhhome";
    }

    @GetMapping(value = "/mhjoin.do")
    public String joinGET() {

        return "/MH/Mhjoin";
    }

    @PostMapping(value = "/mhjoin.do")
    public String joinPOST(@ModelAttribute Member member) {

        // log.info(format, member.toString());

        member.setPassword(bcpe.encode(member.getPassword()));
        
        try {
            mRepository.save(member);
        }

        catch(Exception e) {
            e.printStackTrace();
        } 

        return "redirect:/mhhome.do";
    }

    @GetMapping(value = "/imgtest.do")
    public String imgtestGET(Model model) {

        String email = "test1@gmail.com";

        Member member = mRepository.findById(email).orElse(null);

        model.addAttribute("member", member);

        return "/MH/imgtest";
    }

    @GetMapping(value = "/loginv2.do")
    public String loginv2GET() {

        return "/MH/loginv2";
    }



    // 구글 로그인창 호출
    // @GetMapping(value = "/getGoogleAuthUrl")
    // public @ResponseBody String getGoogleAuthUrl(HttpServletRequest request) throws Exception {

    //     String reqUrl = clientId + "/o/oauth2/v2/auth?client_id=" + loginUrl + "&redirect_uri="
    //             + redirectUrl
    //             + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

    //     return reqUrl;
    // }

}
