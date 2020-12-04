package com.template.app.security;

import com.template.app.dao.User;
import com.template.app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDao userDao;

	@Autowired
	public UserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User: " + userName + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("user")));
	}
}
