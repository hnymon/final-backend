package com.web.jwt.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.domain.Member;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails  implements UserDetails {

    private final Member memberEntity;

    public CustomUserDetails(Member memberEntity) {

        this.memberEntity = memberEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
            	System.out.println("getRole "+memberEntity.getRole());
                return memberEntity.getRole().toString();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
    	System.out.println("getUsername "+memberEntity.getUsername());
        return memberEntity.getUsername();
    }
    
    public String getEmail() {
    	System.out.println("getEmail "+memberEntity.getEmail());
        return memberEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}

