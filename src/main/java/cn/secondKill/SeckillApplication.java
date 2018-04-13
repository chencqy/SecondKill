package cn.secondKill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("cn.secondKill.dao")
@SpringBootApplication
public class SeckillApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SeckillApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SeckillApplication.class);
	}
}
