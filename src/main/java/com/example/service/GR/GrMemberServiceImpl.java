package com.example.service.GR;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.entity.Post;
import com.example.mapper.GR.GrMyblogMapper;
import com.example.repository.GR.GrMemberRepository;
import com.example.repository.GR.GrPostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrMemberServiceImpl implements GrMemberService{
    final GrMyblogMapper mMapper;
    final GrPostRepository pRepository;
    final private GrMemberRepository gRepository; 

    @Override
    public MemberDTO selectMemberOne(String email) {
        try {
            return mMapper.selectMemberOne(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public int countfollowing(String email) {
        try {
            return mMapper.countfollowing(email);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int countfollower(String email) {
        try {
            return mMapper.countfollower(email);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Map<String, Integer>> selectpostcatecount(String email) {
        try {
            return mMapper.selectpostcatecount(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public List<Map<String, Integer>> selectposttag(String email, BigInteger postno) {
        try {
            return mMapper.selectposttag(email, postno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countreply(BigInteger postno) {
        try {
            return mMapper.countreply(postno);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }   
    }

    @Override
    public List<Map<BigInteger, String>> selecttag(BigInteger postno) {
        try {
            return mMapper.selecttag(postno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> findByWriterOrderByNoDesc(String writer) {
        try {
            return pRepository.findByWriterOrderByNoDesc(writer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public List<Post> findByPostTagListTag(String tag) {
        try {
            return pRepository.findByPostTagListTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public List<Post> findByWriterAndCateNoOrderByNoDesc(String writer, BigInteger cateno) {
        try {
            return pRepository.findByWriterAndCateNoOrderByNoDesc(writer, cateno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    
}
