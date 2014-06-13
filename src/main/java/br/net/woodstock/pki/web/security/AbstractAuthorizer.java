package br.net.woodstock.pki.web.security;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.PrincipalCollection;

public abstract class AbstractAuthorizer implements Authorizer {

	public AbstractAuthorizer() {
		super();
	}

	// Override
	@Override
	public boolean isPermitted(final PrincipalCollection principals, final String permission) {
		return false;
	}

	@Override
	public boolean isPermitted(final PrincipalCollection subjectPrincipal, final Permission permission) {
		return false;
	}

	@Override
	public void checkPermission(final PrincipalCollection subjectPrincipal, final String permission) {
		//
	}

	@Override
	public void checkPermission(final PrincipalCollection subjectPrincipal, final Permission permission) {
		//
	}

	@Override
	public boolean hasRole(final PrincipalCollection subjectPrincipal, final String roleIdentifier) {
		return false;
	}

	@Override
	public void checkRole(final PrincipalCollection subjectPrincipal, final String roleIdentifier) {
		//
	}

	// Default
	@Override
	public boolean[] isPermitted(final PrincipalCollection subjectPrincipal, final String... permissions) {
		int len = permissions.length;
		boolean[] array = new boolean[len];
		for (int i = 0; i < len; i++) {
			array[i] = this.isPermitted(subjectPrincipal, permissions[i]);
		}
		return array;
	}

	@Override
	public boolean[] isPermitted(final PrincipalCollection subjectPrincipal, final List<Permission> permissions) {
		int len = permissions.size();
		boolean[] array = new boolean[len];
		for (int i = 0; i < len; i++) {
			array[i] = this.isPermitted(subjectPrincipal, permissions.get(i));
		}
		return array;
	}

	@Override
	public boolean isPermittedAll(final PrincipalCollection subjectPrincipal, final String... permissions) {
		for (String permission : permissions) {
			boolean b = this.isPermitted(subjectPrincipal, permission);
			if (!b) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isPermittedAll(final PrincipalCollection subjectPrincipal, final Collection<Permission> permissions) {
		for (Permission permission : permissions) {
			boolean b = this.isPermitted(subjectPrincipal, permission);
			if (!b) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void checkPermissions(final PrincipalCollection subjectPrincipal, final String... permissions) {
		for (String permission : permissions) {
			this.checkPermission(subjectPrincipal, permission);
		}
	}

	@Override
	public void checkPermissions(final PrincipalCollection subjectPrincipal, final Collection<Permission> permissions) {
		for (Permission permission : permissions) {
			this.checkPermission(subjectPrincipal, permission);
		}
	}

	@Override
	public boolean[] hasRoles(final PrincipalCollection subjectPrincipal, final List<String> roleIdentifiers) {
		int len = roleIdentifiers.size();
		boolean[] array = new boolean[len];
		for (int i = 0; i < len; i++) {
			array[i] = this.hasRole(subjectPrincipal, roleIdentifiers.get(i));
		}
		return array;
	}

	@Override
	public boolean hasAllRoles(final PrincipalCollection subjectPrincipal, final Collection<String> roleIdentifiers) {
		for (String roleIdentifier : roleIdentifiers) {
			boolean b = this.hasRole(subjectPrincipal, roleIdentifier);
			if (!b) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void checkRoles(final PrincipalCollection subjectPrincipal, final Collection<String> roleIdentifiers) {
		for (String roleIdentifier : roleIdentifiers) {
			this.checkRole(subjectPrincipal, roleIdentifier);
		}
	}

	@Override
	public void checkRoles(final PrincipalCollection subjectPrincipal, final String... roleIdentifiers) {
		for (String roleIdentifier : roleIdentifiers) {
			this.checkRole(subjectPrincipal, roleIdentifier);
		}
	}

}
