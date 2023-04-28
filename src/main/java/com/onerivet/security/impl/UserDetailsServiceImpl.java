package com.onerivet.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.onerivet.entity.User;
import com.onerivet.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByEmail(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
       // return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());	
		return new MyUserDetails(user);
    }

}
