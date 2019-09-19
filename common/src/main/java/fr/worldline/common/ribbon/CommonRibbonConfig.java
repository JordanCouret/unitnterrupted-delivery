package fr.worldline.common.ribbon;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = CommonRibbonConfig.class)
public class CommonRibbonConfig {

	@Value("${eureka.instance.metadata-map.version}")
	public String currentVersion;

	@Bean
	public VersionZoneAffinityServerListFilter serverListFilter() {
		IClientConfig config = DefaultClientConfigImpl.getClientConfigWithDefaultValues();
		config.set(CommonClientConfigKey.EnableZoneAffinity, true);
		config.set(CommonClientConfigKey.MaxAutoRetriesNextServer, 2);
		return new VersionZoneAffinityServerListFilter(config, currentVersion);
	}

}
