package es.local.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.local.spring.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final es.local.spring.entities.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user don't exist"));
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities("Admin").build();
		return userDetails;
	}
	
	public void save (es.local.spring.entities.User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public es.local.spring.entities.User findByUsername(String username) {
		es.local.spring.entities.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user don't exist"));
		return user;
	}
	
	

}
