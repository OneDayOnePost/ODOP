package com.example.service.AR;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.entity.Member;
import com.example.repository.AR.ArMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArMemberServiceImpl implements ArMemberService {
    final ArMemberRepository mRepository;

   	// 회원가입
	@Override
	public Member insertMember(Member member) {
		try {
			return mRepository.save(member);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    //이메일 중복 확인
    @Override
    public int selectMemberEmailCheck(String email) {
        try{
            return mRepository.countByEmail(email);
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //닉네임 중복 확인
    @Override
    public int selectMemberNicknameCheck(String nickname) {
        try {
            return mRepository.countByNickname(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    //로그인(loginfailurehandler alert 관련)
    @Override
    public Member findByEmail(String email) {
        try {
            return mRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Member selectMemberEmail(String name, String phone) {
        try {
            return mRepository.findByNameAndPhone(name, phone);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
