package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SistConfig implements WebMvcConfigurer{
	
	@Autowired
	private MemberIntercept memberIntercept;
	
	public void setMemberIntercept(MemberIntercept memberIntercept) {
		this.memberIntercept = memberIntercept;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(memberIntercept)
		.addPathPatterns("/member/**")
		.excludePathPatterns("/member/images/**");
	}
}
