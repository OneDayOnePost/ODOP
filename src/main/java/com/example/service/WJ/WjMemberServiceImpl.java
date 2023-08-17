package com.example.service.WJ;

import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.repository.WJ.WjMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjMemberServiceImpl implements WjMemberService {
    final WjMemberRepository mRepository;

    // 멤버 한 명 조회 (이메일)
    @Override
    public Member findByEmail(String email) {
        try {
            return mRepository.findByEmail(email);
        }
        catch (Exception e) {
            e.printStackTrace();;
            return null;
        }
    }

    // 멤버 한 명 조회 (닉네임)
    @Override
    public Member findByNickname(String nickname) {
        try {
            return mRepository.findByNickname(nickname);
        }
        catch (Exception e) {
            e.printStackTrace();;
            return null;
        }
    }
    
}
