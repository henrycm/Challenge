package com.jhcm.appdirect.backend.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class OpenIDUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		return new User(token.getName(), "", AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("Test", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

}