package com.codestates.hobby.global.config;

import static org.springframework.security.config.Customizer.*;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.codestates.hobby.domain.auth.filter.JsonAuthenticationFilter;
import com.codestates.hobby.domain.auth.handler.CustomLoginFailureHandler;
import com.codestates.hobby.domain.auth.handler.CustomLoginSuccessHandler;
import com.codestates.hobby.domain.auth.service.LoginService;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final ObjectMapper objectMapper;
	private final LoginService loginService;
	private final MemberRepository memberRepository;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin()
			.and()
			.csrf().disable() //로그인 시 post 요청이라 해제해도 됨
			.cors(withDefaults())
			.formLogin().disable()
			.httpBasic().disable();
		http.authorizeHttpRequests(authorize -> authorize
			// TODO: 추가하기
			.mvcMatchers(HttpMethod.GET, "**").permitAll()
			.mvcMatchers(HttpMethod.POST, "/members").permitAll()
			.anyRequest().authenticated());
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false);
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("logout"))
			.invalidateHttpSession(true);
		http.addFilterAfter(jsonLoginFilter(), LogoutFilter.class);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(loginService);
		return new ProviderManager(provider);
	}

	@Bean
	public JsonAuthenticationFilter jsonLoginFilter() {
		JsonAuthenticationFilter jsonLoginFilter = new JsonAuthenticationFilter(objectMapper);
		jsonLoginFilter.setAuthenticationManager(authenticationManager());
		jsonLoginFilter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler(memberRepository));
		jsonLoginFilter.setAuthenticationFailureHandler(new CustomLoginFailureHandler());
		return jsonLoginFilter;
	}
}
