package order;

import static java.util.Collections.emptyList;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import order.dao.UserRepository;
import order.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws AuthenticationException {
		User user = userRepository.findByUsername(email);
		if (user == null) {
			user = userRepository.findByEmail(email);
			if(user==null)
			throw new UsernameNotFoundException(email);
		}
//		if(user!=null&&!user.getIsactive()) {
//			throw new UserDisabledException("user is not active");
//		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				emptyList());
	}
}
