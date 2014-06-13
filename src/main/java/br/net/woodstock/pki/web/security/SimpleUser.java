package br.net.woodstock.pki.web.security;

import java.io.Serializable;

public interface SimpleUser extends Serializable {

	String getName();

	String[] getRoles();

}
