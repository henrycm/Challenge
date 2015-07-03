package com.jhcm.appdirect.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class OpenIDUserDetailsService implements
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {
		String userName = "";

		for (OpenIDAttribute a : token.getAttributes()) {
			if (a.getName().equals("firstName"))
				userName += a.getValues().get(0) + " ";
			if (a.getName().equals("lastName"))
				userName += a.getValues().get(0) + " ";
		}
		return new User(userName, "",
				AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

}
