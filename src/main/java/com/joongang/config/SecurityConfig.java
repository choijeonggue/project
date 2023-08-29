package com.joongang.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.joongang.security.CustomUserDetailService;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@ComponentScan("com.joongang.security")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	// Spring Security의 웹 보안 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/guest/**").permitAll()
//			.antMatchers("/member/**").access("hasRole('ROLE_MEMBER')")
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("user/login")
			.usernameParameter("memberId")
			.passwordParameter("memberPwd") // 스프링 시큐리티에서 제공하는 로그인 폼
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler);

		http.logout().logoutUrl("/logout").invalidateHttpSession(true)
			.deleteCookies("remember-me", "JSESSION_ID");
		
		http.rememberMe().key("joongang")
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
		
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl; 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService)
			.passwordEncoder(passwordEncoder()); 
			
	}
			
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
}
