package challenge.preonboarding.wanted.global.config;

import challenge.preonboarding.wanted.global.dto.response.ResponseDto;
import challenge.preonboarding.wanted.global.filter.JwtAuthFilter;
import challenge.preonboarding.wanted.global.filter.JwtExceptionFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static challenge.preonboarding.wanted.global.constant.StatusCode.NO_AUTHENTICATION;
import static challenge.preonboarding.wanted.global.constant.StatusCode.NO_PERMISSION;
import static challenge.preonboarding.wanted.global.util.JsonUtil.objectMapper;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final String[] anonymousApiList = {

    };

    private final String[] memberApiList = {

    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .formLogin(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .sessionManagement((sessionManagement) -> {
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
                
            // 인증, 인가 부분
            .authorizeHttpRequests((auth) -> { auth
                // 인증, 인가 필요 없는 API
                .requestMatchers(anonymousApiList).permitAll()

                // 사용자 인증이 필요한 API
                .requestMatchers(memberApiList).hasRole("USER")

                // 그 외의 페이지는 모두 허용
                .anyRequest().permitAll();})

            // 인증, 인가 실패 시, 403 or 403 응답
            .exceptionHandling((error) -> {
                error.authenticationEntryPoint(CustomAuthenticationEntryPoint())
                     .accessDeniedHandler(CustomAccessDeniedHandler());
            })
                
            // JWT 인증, 예외 필터 추가
            .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JwtExceptionFilter(), JwtAuthFilter.class)
                
            // 빌드
            .build();
    }

    // 401 에러 응답 Bean
    @Bean
    public AuthenticationEntryPoint CustomAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                ResponseDto<String> responseDto = new ResponseDto<>(NO_AUTHENTICATION.getCode(), NO_AUTHENTICATION.getMsg(), null);

                // HTTP 응답 (401)
                response.setStatus(NO_AUTHENTICATION.getStatus().value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                response.getWriter().write(objectMapper.writeValueAsString(responseDto));
            }
        };
    }

    // 403 에러 응답 Bean
    @Bean
    public AccessDeniedHandler CustomAccessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                ResponseDto<String> responseDto = new ResponseDto<>(NO_PERMISSION.getCode(), NO_PERMISSION.getMsg(), null);

                // HTTP 응답 (403)
                response.setStatus(NO_PERMISSION.getStatus().value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                response.getWriter().write(objectMapper.writeValueAsString(responseDto));
            }
        };
    }
}
