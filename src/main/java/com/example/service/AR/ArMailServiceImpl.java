package com.example.service.AR;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class ArMailServiceImpl implements ArMailService{
 
    @Autowired
    JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public static final String authNum = createKey();

    //인증번호 8자리 무작위 생성
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
 
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(9)));
                    // 0~9
                    break;
            }
        }
       return key.toString();
    }


    
    // 실제 메일 전송
    @Override
    public String sendSimpleMessage(String email) throws Exception {
        // HTML 템플릿 렌더링
        Context context = new Context();
        context.setVariable("authNum", authNum);
        String content = templateEngine.process("message", context);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String setFrom = "odop.onedayonepost@gmail.com";
        String toEmail = email;
        String title = "ODOP 이메일 인증 번호";

        try {
            helper.setFrom(setFrom);
            helper.setTo(toEmail); // 받는 사람 설정
            helper.setSubject(title); // 제목 설정
            helper.setText(content, true); // HTML 내용 설정
            emailSender.send(message); // 메일 전송
        } catch (MailException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Failed to send email.");
        }
        return authNum;
    }


}