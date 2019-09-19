package fr.worldline.gateway;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.Server;

import org.springframework.cloud.netflix.ribbon.eureka.EurekaServerIntrospector;


public class VersionAvoidancePredicate extends CompositePredicate {

	private final EurekaServerIntrospector introspector = new EurekaServerIntrospector();

	public VersionAvoidancePredicate() {
	}

	@Override
	public List<Server> getEligibleServers(List<Server> servers, Object loadBalancerKey) {
		final String targetVersion = String.valueOf(loadBalancerKey);
		return servers.stream()
				.filter(server -> targetVersion.equalsIgnoreCase(introspector.getMetadata(server).get("version")))
				.collect(Collectors.toList());
	}
}
