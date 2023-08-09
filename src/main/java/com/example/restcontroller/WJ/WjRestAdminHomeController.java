package com.example.restcontroller.WJ;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PostDTO;
import com.example.dto.ReplyDTO;
import com.example.entity.Member;
import com.example.service.WJ.WjReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class WjRestAdminHomeController {
    final WjReportService rService;
    
    // 신고된 게시글 상세 페이지
    // (1) 삭제 승인
    @PutMapping(value = "/postdelete.json")
    public Map<String, Object> postdeletePUT(@RequestBody PostDTO post) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.postDelete(post.getNo());

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

    // (2) 삭제 거절 -> post_report 테이블에서 해당 게시글 삭제
    @DeleteMapping(value = "/postreportdelete.json")
    public Map<String, Object> postreportdeleteDELETE(@RequestParam(name = "no") String no) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            BigInteger postno = new BigInteger(no);
            int ret = rService.postReportDelete(postno);

            if (ret >= 1) {
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

    // (3) 삭제 취소
    @PutMapping(value = "/postdeletecancel.json")
    public Map<String, Object> postdeletecancelPUT(@RequestBody PostDTO post) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.postDeleteCancel(post.getNo());

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

    // --------------------------------------------------------------------

    // 신고된 댓글 상세 페이지
    // (1) 삭제 승인
    @PutMapping(value = "/replydelete.json")
    public Map<String, Object> replydeletePUT(@RequestBody ReplyDTO reply) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.replyDelete(reply.getNo());

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

    // (2) 삭제 거절 -> reply_report 테이블에서 해당 게시글 삭제
    @DeleteMapping(value = "/replyreportdelete.json")
    public Map<String, Object> replyreportdeleteDELETE(@RequestParam(name = "no") String no) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            BigInteger replyno = new BigInteger(no);
            int ret = rService.replyReportDelete(replyno);

            if (ret >= 1) {
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

    // (3) 삭제 취소
    @PutMapping(value = "/replydeletecancel.json")
    public Map<String, Object> replydeletecancelPUT(@RequestBody ReplyDTO reply) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.replyDeleteCancel(reply.getNo());

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

    // --------------------------------------------------------------------

    // 회원 상세 페이지
    // (1) 블랙리스트 등록 버튼
    @PutMapping(value = "/updateblacklist.json")
    public Map<String, Object> updateblacklistPUT(@RequestBody Member member) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.updateToBlackList(member.getEmail());

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

    // (2) 블랙리스트 등록 취소 버튼
    @PutMapping(value = "/updategraylist.json")
    public Map<String, Object> updategraylistPUT(@RequestBody Member member) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.updateToGrayList(member.getEmail());

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
