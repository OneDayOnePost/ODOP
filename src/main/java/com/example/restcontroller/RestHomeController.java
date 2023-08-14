package com.example.restcontroller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/home")
public class RestHomeController {

		@GetMapping(value = "/getlist.json")
		public Map<String, Object> getListGET(
						// @RequestParam Map<String, Object> params
		@RequestParam(name = "category", required = false) List<BigInteger> category,
		@RequestParam(name = "mbti", required = false) List<String> mbti,
		@RequestParam(name = "type", required = false) String type,
		@RequestParam(name = "option", required = false) String option,
		@RequestParam(name = "keywords", required = false) List<String> keywords,
		@RequestParam(name = "email", required = false) String email 
		) throws UnsupportedEncodingException {

				Map<String, Object> retMap = new HashMap<>();

				// log.info("getListGET => {}", category.toString());

				urlDecode(keywords);

				log.info("getListGET => {}", mbti.toString());
				log.info("getListGET => {}", category.toString());
				log.info("getListGET => {}", option.toString());   
				log.info("getListGET => {}", type.toString());
				log.info("getListGET => {}", email.toString());
				log.info("getListGET => {}", urlDecode(keywords));

				Map<String, Object> params = new HashMap<>();

				params.put("category", category);
				params.put("mbti", mbti);
				params.put("option", option);
				params.put("type", type);
				params.put("email", email);
				params.put("keywords", urlDecode(keywords));
				
				log.info("getListGET => {}", params.toString());

				retMap.put("status", 1);

				return retMap;
		}

		private List<String> urlDecode(List<String> keywords) throws UnsupportedEncodingException {

				for(int i=0; i < keywords.size(); i++) {

						String keyword = keywords.get(i);
						keyword = URLDecoder.decode(keyword, "UTF-8");
						keywords.set(i, keyword);

				}

				return keywords;
		}

}
