package me.bdh.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class FileConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/file/**") // 클라이언트 요청 주소 패턴
		.addResourceLocations("file:///D:/uploadFiles/test/");
		
	}
}
