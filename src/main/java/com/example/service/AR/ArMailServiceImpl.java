package com.example.service.AR;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class ArMailServiceImpl implements ArMailService{
 
    @Autowired
    JavaMailSender emailSender;
 
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


    // 메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
        // 코드를 생성합니다.
        createKey();
        String setFrom = "odop.onedayonepost@gmail.com";	// 보내는 사람
        String toEmail = email;		// 받는 사람(값 받아옵니다.)
        String title = "ODOP 이메일 인증 번호";		// 메일 제목		

        MimeMessage message = emailSender.createMimeMessage();
        
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);	// 받는 사람 설정
        message.setSubject(title);		// 제목 설정

        // 메일 내용 설정
        String msgOfEmail="";
        msgOfEmail+= "<div style='margin:20px; text-align:center;'>";
        msgOfEmail+= "<h1> 안녕하세요 </h1>";
        msgOfEmail+= "<h1 style='color: #1D3563;'> OneDayOnePost, ODOP </h1>";
        msgOfEmail+= "<h1> 입니다. </h1>";
        msgOfEmail+= "<br>";
        msgOfEmail+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgOfEmail+= "<br>";
        msgOfEmail+= "<p>감사합니다.<p>";
        msgOfEmail+= "<br>";
        msgOfEmail+= "<div align='center' style='border:1px solid #9aa2c0; font-family:NanumSquare; background: #ffffff;';>";
        msgOfEmail+= "<h3 style='color: #1D3563;'>이메일 인증 코드입니다.</h3>";
        msgOfEmail+= "<div style='font-size:130%'>";
        msgOfEmail+= " <strong>";
        msgOfEmail+= authNum+"</strong><div><br/> ";
        msgOfEmail+= "</div>";

        message.setFrom(setFrom);		// 보내는 사람 설정
        // 위 String으로 받은 내용을 아래에 넣어 내용을 설정합니다.
        message.setText(msgOfEmail, "utf-8", "html");

        return message;
    }
 
   

    //실제 메일 전송
    @Override
    public String sendSimpleMessage(String toemail) throws Exception {
        MimeMessage message = createEmailForm(toemail);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return authNum;
    }

}