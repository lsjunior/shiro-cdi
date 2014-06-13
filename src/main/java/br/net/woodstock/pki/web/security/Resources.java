package br.net.woodstock.pki.web.security;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class Resources {

	public Resources() {
		super();
	}

	@Produces
	@SessionScoped
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}

}
