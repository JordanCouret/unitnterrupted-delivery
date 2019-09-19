package fr.worldline.gateway;

import com.netflix.loadbalancer.IRule;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RibbonClients(defaultConfiguration = RibbonGatewayConfig.class)
@Configuration
public class RibbonGatewayConfig {
	@Bean
	public IRule ribbonRule() {
		return new VersionAvoidanceRule();
	}
}
