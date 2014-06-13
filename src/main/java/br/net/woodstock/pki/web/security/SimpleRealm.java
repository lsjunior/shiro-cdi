package br.net.woodstock.pki.web.security;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

@LocalBean
@Stateless
public class SimpleRealm implements Realm {
	
	private static final String REALM_NAME = "SIMPLE";

	@EJB
	private SimpleUserService	userService;

	public SimpleRealm() {
		super();
	}

	@Override
	public String getName() {
		return SimpleRealm.REALM_NAME;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(final AuthenticationToken token) {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String username = userToken.getUsername();
		char[] password = userToken.getPassword();
		
		if((username != null) && (!username.isEmpty()) && (password != null) && (password.length > 0)) {
			SimpleUser user = this.userService.getByUsernamePassword(username, new String(password));
			if (user != null) {
				AuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getName());
				return info;
			}
		}

		return null;
	}

	@Override
	public boolean supports(final AuthenticationToken token) {
		if (token instanceof UsernamePasswordToken) {
			return true;
		}
		return false;
	}
	
}