/**
 * 
 */
package com.senlang.sdip.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

/**
 * @author Mc.D
 *
 */
@AllArgsConstructor
public class JwtUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394842320915867785L;
	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return "123456";
	}

	@Override
	public String getUsername() {
		return username;
	}
}
