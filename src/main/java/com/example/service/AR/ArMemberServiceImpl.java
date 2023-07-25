package com.example.service.AR;

import org.springframework.stereotype.Service;

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


    

    
}
