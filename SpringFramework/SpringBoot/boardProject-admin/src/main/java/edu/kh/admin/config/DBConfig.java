package edu.kh.admin.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/config.properties")
public class DBConfig {
	
	/////////// HikariCP 설정 /////////////
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		
		// HikariConfig 설정 객체 생성
		// -> config.properties 파일에서 읽어온
		//    spring.datasource.hikari로 시작하는 모든 값이
		//    알맞은 필드에 세팅된 상태
		return new HikariConfig(); 
	}
	
	
	@Bean
	public DataSource dataSource(HikariConfig config) {
		// 매개 변수 HikariConfig config
		// -> 등록된 Bean 중 HikariConfig 타입의 Bean이 자동으로 주입
		
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
}
