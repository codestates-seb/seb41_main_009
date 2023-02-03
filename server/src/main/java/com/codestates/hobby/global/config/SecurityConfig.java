package com.codestates.hobby.global.config;

import static org.springframework.security.config.Customizer.*;

import java.util.List;

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
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.codestates.hobby.domain.auth.filter.JsonAuthenticationFilter;
import com.codestates.hobby.domain.auth.handler.CustomLoginFailureHandler;
import com.codestates.hobby.domain.auth.handler.CustomLoginSuccessHandler;
import com.codestates.hobby.domain.auth.service.UserDetailsServiceImpl;
import com.codestates.hobby.global.log.LoggingFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final ObjectMapper objectMapper;
	private final UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin()
			.and()
			.csrf().disable()
			.cors(withDefaults())
			.formLogin().disable()
			.httpBasic().disable();
		http.authorizeHttpRequests(authorize -> authorize
			.mvcMatchers("/auth/certifications").permitAll()
			.mvcMatchers(HttpMethod.POST, "/members").permitAll()
			.mvcMatchers(HttpMethod.GET, "/members", "/series", "/showcases", "/posts", "/categories").permitAll()
			.mvcMatchers(HttpMethod.GET, "/members/**", "/series/**", "/showcases/**", "/posts/**", "/categories/**").permitAll()
			.anyRequest().authenticated());
		http.sessionManagement()
			.sessionFixation().changeSessionId()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false);
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("logout"))
			.invalidateHttpSession(true);
		http.addFilterAfter(jsonLoginFilter(), LogoutFilter.class)
			.addFilterBefore(loggingFilter(), SecurityContextHolderFilter.class);

		return http.build();
	}

	@Bean
	LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
			List.of("http://localhost:3000", "http://127.0.0.1:3000",
				"http://intorest.s3-website.ap-northeast-2.amazonaws.com", "http://intorestbackup.s3-website.ap-northeast-2.amazonaws.com"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS", "HEAD"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Cache-Control"));
		configuration.setExposedHeaders(List.of("Authorization"));
		configuration.setAllowCredentials(true);

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
		provider.setUserDetailsService(userDetailsService);
		return new ProviderManager(provider);
	}

	@Bean
	public JsonAuthenticationFilter jsonLoginFilter() {
		JsonAuthenticationFilter jsonLoginFilter = new JsonAuthenticationFilter(objectMapper);
		jsonLoginFilter.setAuthenticationManager(authenticationManager());
		jsonLoginFilter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler());
		jsonLoginFilter.setAuthenticationFailureHandler(new CustomLoginFailureHandler());
		return jsonLoginFilter;
	}
}
