package com.example.restcontroller.GR;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class GrRestMyBlogController {

    // @GetMapping(value = "/myblog.json")
    // public Map<String, Integer> myblogGET() {

    // }

}
