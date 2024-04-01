package edu.kh.demo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// @PropertySource : properties 파일의 내용을 이용하겠다는 어노테이션
// - 다른 properties도 추가하고 싶으면 어노테이션을 계속 추가

// @Bean
// - 개발자가 수동으로 bean을 등록하는 어노테이션
// - @Bean 어노테이션이 작성된 메서드에서 반환된 객체는
// Spring Container가 관리함(IOC)

// @ConfigurationProperties(prefix = "spring.datasource.hikari")
// - properties 파일의 내용을 이용해서 생성되는 bean을 설정하는 어노테이션
// - prefix를 지정하여 spring.datasource.hikari으로 시작하는 설정을 모두 적용

// @Autowired (DI, 의존성 주입)
// - 등록된 Bean 중 타입이 일치하거나 상속 관계인 Bean을
//  해당 변수에 주입하라는 어노테이션

@Configuration  // 설정용 클래스(서버 켜지자 마자 실행)
@PropertySource("classpath:/config.properties")
public class DBConfig {
	
	@Autowired // 필드를 이용한 의존성 주입 (DI)
	private ApplicationContext applicationContext;

	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	// DataSource : Connection을 생성 + Connection Pool 지원 (DriverManager 대안, Java JNDI를 이용한 기술)
	// Connection Pool : 커넥션을 미리 만들어두고 필요한 상황에 대여/반납을 받는 방법
	//					-> 요청 마다 새로운 커넥션을 만들 필요 없음 -> 생성/반환에 소요되는 시간/메모리 단축
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	//////////////////////////// Mybatis 설정 추가 ////////////////////////////
	
	// SqlSessionFactory : SqlSession을 만드는 객체 
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource);
		
		// 매퍼 파일이 모여있는 경로 지정
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		
		// 별칭을 지정해야하는 DTO가 모여있는 패키지 지정
		// -> 해당 패키지에 있는 모든 클래스가 클래스명으로 별칭이 지정됨
		sessionFactoryBean.setTypeAliasesPackage("edu.kh.demo");
		
		// 마이바티스 설정 파일 경로 지정
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		
		// SqlSession 객체 반환
		return sessionFactoryBean.getObject();
	}
	
	
	
	// SqlSessionTemplate :  기본 SQL 실행 + 트랜잭션 처리
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}
	
	// DataSourceTransactionManager : 트랜잭션 매니저
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
