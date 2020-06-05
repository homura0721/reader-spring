package cn.edu.scujcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class ReaderSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaderSpringApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
}
