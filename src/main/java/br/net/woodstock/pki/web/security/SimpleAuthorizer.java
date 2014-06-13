package br.net.woodstock.pki.web.security;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.PrincipalCollection;

@LocalBean
@Stateless
public class SimpleAuthorizer extends AbstractAuthorizer {

	public SimpleAuthorizer() {
		super();
	}

	@Override
	public void checkRole(final PrincipalCollection principals, final String roleIdentifier) {
		if (!this.hasRole(principals, roleIdentifier)) {
			throw new UnauthorizedException("Subject does not have role [" + roleIdentifier + "]");
		}
	}

	@Override
	public boolean hasRole(final PrincipalCollection principals, final String roleIdentifier) {
		SimpleUser principal = (SimpleUser) principals.getPrimaryPrincipal();
		if (principal != null) {
			String[] roles = principal.getRoles();
			if ((roles != null) && (roles.length > 0)) {
				for (String role : roles) {
					if (role.equals(roleIdentifier)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}