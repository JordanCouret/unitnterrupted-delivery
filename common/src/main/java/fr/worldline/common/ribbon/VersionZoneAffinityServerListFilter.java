package fr.worldline.common.ribbon;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAffinityServerListFilter;

import org.springframework.cloud.netflix.ribbon.eureka.EurekaServerIntrospector;

public class VersionZoneAffinityServerListFilter extends ZoneAffinityServerListFilter<Server> {

	private final EurekaServerIntrospector introspector;

	private final String currentVersion;

	public VersionZoneAffinityServerListFilter(IClientConfig iClientConfig, String currentVersion) {
		super(iClientConfig);
		this.introspector = new EurekaServerIntrospector();
		this.currentVersion = currentVersion;
	}

	@Override
	public List<Server> getFilteredListOfServers(List<Server> servers) {
		// Filter version base on the current version of the microservice
		List<Server> serverWithAffinity = servers
				.stream()
				.filter(server -> this.currentVersion.equalsIgnoreCase(this.introspector.getMetadata(server).get("version")))
				.collect(Collectors.toList());
		// Affinity of zone
		return super.getFilteredListOfServers(serverWithAffinity);
	}
}
