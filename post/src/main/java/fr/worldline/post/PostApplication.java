package fr.worldline.post;

import fr.worldline.common.ribbon.CommonRibbonConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import(CommonRibbonConfig.class)
@EnableFeignClients(basePackageClasses = CommentApi.class)
@EnableEurekaClient
@SpringBootApplication
public class PostApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostApplication.class, args);
	}

}
