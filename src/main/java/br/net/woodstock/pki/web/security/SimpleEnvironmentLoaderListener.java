package br.net.woodstock.pki.web.security;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.shiro.mgt.AuthorizingSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

public class SimpleEnvironmentLoaderListener extends EnvironmentLoaderListener {

	@Inject
	private SimpleAuthorizer	authorizer;

	@Inject
	private SimpleRealm			realm;

	public SimpleEnvironmentLoaderListener() {
		super();
	}

	@Override
	protected WebEnvironment createEnvironment(final ServletContext sc) {
		WebEnvironment environment = super.createEnvironment(sc);
		AuthorizingSecurityManager securityManager = (AuthorizingSecurityManager) environment.getSecurityManager();
		securityManager.setAuthorizer(this.authorizer);
		securityManager.setRealm(this.realm);
		return environment;
	}

}
