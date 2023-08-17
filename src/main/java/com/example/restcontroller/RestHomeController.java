package com.example.restcontroller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PostAllViewDTO;
import com.example.dto.PostDTO;
import com.example.entity.Alert;
import com.example.entity.Member;
import com.example.service.HomeService;
import com.example.service.WJ.WjAlertService;
import com.example.service.WJ.WjMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/home")
public class RestHomeController {

	final HomeService homeService;

	final WjAlertService WjaService;
    final WjMemberService WjmService;

	@GetMapping(value = "/getlist.json")
	public Map<String, Object> getListGET(
			// @RequestParam Map<String, Object> params
			@RequestParam(name = "category", required = false) List<BigInteger> category,
			@RequestParam(name = "mbti", required = false) List<String> mbti,
			@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "option", required = false) String option,
			@RequestParam(name = "keywords", required = false) List<String> keywords,
			@RequestParam(name = "email", required = false) String email) throws UnsupportedEncodingException {

		Map<String, Object> retMap = new HashMap<>();

		/* retMap 초기 반환값 설정 */
		retMap.put("status", -1);

		/* URLDecode 함수 */
		urlDecode(keywords);

		/* 파라미터 출력 및 확인 */ 
		// log.info("getListGET => {}", mbti.toString());
		// log.info("getListGET => {}", category.toString());
		// log.info("getListGET => {}", option.toString());
		// log.info("getListGET => {}", type.toString());
		// log.info("getListGET => {}", email.toString());
		// log.info("getListGET => {}", urlDecode(keywords));

		/* 파라미터를 모두 저장할 Map 객체 생성 */
		Map<String, Object> params = new HashMap<>();

		params.put("category", category);
		params.put("mbti", mbti);
		params.put("option", option);
		params.put("type", type);
		params.put("email", email);
		params.put("keywords", urlDecode(keywords));

		/* 생성된 Map 객체 출력 및 확인 */
		log.info("getListGET => {}", params.toString());

		/* 게시글 조회 함수 호출 및 반환 */
		List<PostAllViewDTO> list = homeService.selectPostList(params);

		/* List 객체가 비어 있지 않을 경우, status 200 */
		if(!list.isEmpty()) {
			retMap.put("status", 200);
			retMap.put("list", list);
		}

		return retMap;
	}

	private List<String> urlDecode(List<String> keywords) throws UnsupportedEncodingException {

		for (int i = 0; i < keywords.size(); i++) {

			String keyword = keywords.get(i);
			keyword = URLDecoder.decode(keyword, "UTF-8");
			keywords.set(i, keyword);

		}

		return keywords;
	}

	// header - 알림
	// 알림 리스트 조회
	@GetMapping(value = "/alert.json")
    public Map<String, Object> alertGET(@AuthenticationPrincipal User user, @RequestParam(name = "useremail") String useremail){
        Map<String, Object> retMap = new HashMap<>();

        try {
			if (user != null) {
				List<Alert> alertList = WjaService.selectAlertList(useremail);
				Date now = new Date();

				// 원하는 날짜 포맷을 지정하는 SimpleDateFormat 생성
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
	
				for (Alert alert : alertList) {
					// 프로필 이미지
					// alert 테이블의 content -> "님이" 이전의 문자열을 추출 -> 닉네임
					String fromNickname = alert.getContent().substring(0, alert.getContent().indexOf("님이"));
	
					Member member = WjmService.findByNickname(fromNickname);
	
					if (member != null) {
						alert.setImgkey(member.getImgkey());
						alert.setImgpath(member.getImgpath());
					}
					
					// 등록 날짜
					Date regdate = alert.getRegdate();

					// 현재 날짜와 regdate의 차이를 분 단위로 계산
					long timeDiff = (now.getTime() - regdate.getTime()) / (60 * 1000);
					
					if (timeDiff < 1) { // 1분 미만
						alert.setDiffRegdate("방금 전");
					} else if (timeDiff < 60) { // 1시간 미만
						alert.setDiffRegdate(timeDiff + "분 전");
					} else if (timeDiff < 1440) { // 1일 미만
						alert.setDiffRegdate((timeDiff / 60) + "시간 전");
					} else if (timeDiff < 10080) { // 일주일 미만
						alert.setDiffRegdate((timeDiff / 1440) + "일 전");
					} else { // 일주일 이후
						alert.setDiffRegdate(dateFormat.format(regdate));
					}
				}
	
				retMap.put("status", 200);
				retMap.put("alertList", alertList);
			}
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

	// 알림 상태 업데이트 (chk=0 -> chk=1)
	@PutMapping(value = "/updatealertchk.json")
    public Map<String, Object> updatealertchkPUT(@RequestBody Alert alert) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = WjaService.updateAlertChk(alert.getNo());

            if (ret == 1) {
                retMap.put("status", 200);
            } else {
                retMap.put("status", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }
}
