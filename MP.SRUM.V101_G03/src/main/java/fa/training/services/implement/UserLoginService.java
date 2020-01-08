package fa.training.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.training.models.User;
import fa.training.repositories.UserRepository;

@Service
public class UserLoginService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByAccount(username).orElseThrow(() -> {
      throw new UsernameNotFoundException("Account not found");
    });
    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getAccount(),
        user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
    return userDetails;
  }

}
