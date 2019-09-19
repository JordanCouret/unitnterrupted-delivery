package fr.worldline.gateway;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.PredicateBasedRule;

public class VersionAvoidanceRule extends PredicateBasedRule {

	private final CompositePredicate compositePredicate;

	public VersionAvoidanceRule() {
		super();
		compositePredicate = new VersionAvoidancePredicate();
	}

	@Override
	public AbstractServerPredicate getPredicate() {
		return this.compositePredicate;
	}
}
