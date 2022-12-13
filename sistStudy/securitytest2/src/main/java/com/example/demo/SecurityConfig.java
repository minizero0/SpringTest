package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		//아무나 사용가능
		http.authorizeHttpRequests().mvcMatchers("/","/all/**", "/join").permitAll()	
		.mvcMatchers("/admin/**").hasRole("admin")			
		.anyRequest().authenticated();				//나머지는 로그인만하면 가능
		
		
		//로그인 설정
		http.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/service1");
		
		
		//로그아웃 설정
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
	}

}
