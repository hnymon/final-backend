package com.web.oauth2.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties.SameSite;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.web.domain.Member;
import com.web.domain.Role;
import com.web.jwt.JWTUtil;
import com.web.oauth2.CustomOAuth2User;
import com.web.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final MemberRepository mRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            // User의 Role이 GUEST일 경우 DB저장
            if(oAuth2User.getRole() == Role.GUEST) {
                String accessToken = jwtUtil.createAccessToken(oAuth2User.getMemberNum());
                String refreshToken = jwtUtil.createRefreshToken();
                System.out.println("엑세스토큰 발급 완료");

                jwtUtil.sendAccessAndRefreshToken(response, accessToken, null);
                Member findMember = Optional.of(mRepo.findByEmail(oAuth2User.getEmail()))
                                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저가 없습니다."));
                findMember.authorizeUser();
                findMember.updateRefreshToken(refreshToken);
                mRepo.save(findMember);
                
                
                response.sendRedirect(UriComponentsBuilder.fromUriString("http://www.starbook.p-e.kr/login/callback")
                		.queryParam("accessToken", accessToken)
                		.queryParam("refreshToken", refreshToken)
                		.build()
                		.encode(StandardCharsets.UTF_8)
                		.toUriString());
                
            } else {
            	String accessToken = jwtUtil.createAccessToken(oAuth2User.getMemberNum());
            	String refreshToken = jwtUtil.createRefreshToken();
            	Member findMember = mRepo.findByEmail(oAuth2User.getEmail());
            	findMember.updateRefreshToken(refreshToken);
                
                
            	response.sendRedirect(UriComponentsBuilder.fromUriString("http://www.starbook.p-e.kr/login/callback")
                		.queryParam("accessToken", accessToken)
                		.queryParam("refreshToken", refreshToken)
                		.build()
                		.encode(StandardCharsets.UTF_8)
                		.toUriString());
            }
            
        } catch (Exception e) {
            throw e;
        }
    }

}
