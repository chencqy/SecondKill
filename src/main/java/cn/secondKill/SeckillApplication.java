package cn.secondKill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("cn.secondKill.dao")
@EnableSwagger2
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
