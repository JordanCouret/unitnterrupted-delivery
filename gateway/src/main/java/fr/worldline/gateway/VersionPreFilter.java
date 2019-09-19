package fr.worldline.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VersionPreFilter extends ZuulFilter {

	@Value("${eureka.instance.metadata-map.version}")
	private String currentVersion;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		return !requestContext.containsKey(FilterConstants.FORWARD_TO_KEY) // a filter has already forwarded
				&& !requestContext.containsKey(FilterConstants.LOAD_BALANCER_KEY); // a filter has already determined serviceId;
	}

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		String version = requestContext.getRequest().getHeader("X-VERSION");

		if (!StringUtils.hasLength(version)) {
			version = currentVersion;
		}

		requestContext.put(FilterConstants.LOAD_BALANCER_KEY, version);
		return null;
	}
}
