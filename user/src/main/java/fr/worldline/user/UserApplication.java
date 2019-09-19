package fr.worldline.user;

import fr.worldline.common.ribbon.CommonRibbonConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import(CommonRibbonConfig.class)
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = PostApi.class)
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
