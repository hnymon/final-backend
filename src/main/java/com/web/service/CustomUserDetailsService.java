package com.web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.domain.MemberEntity;
import com.web.dto.CustomUserDetails;
import com.web.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberEntity userData = memberRepository.findByUsername(username);

        if (userData != null) {
        	System.out.println("userData"+userData);
            return new CustomUserDetails(userData);
        }


        return null;
    }
}
