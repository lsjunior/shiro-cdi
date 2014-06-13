package br.net.woodstock.pki.web.security;

public interface SimpleUserService {

	SimpleUser getByUsernamePassword(String username, String password);

}
