package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.model.User;
import miu.edu.badgesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getUserByName(username);

        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("Sorry,User by name: "+username+" not found");
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationForLogin(user.getUsername(),
                user.getId()));
        return new CustomUserDetails(user);
    }

    private User getUserByName(String username){
       return userRepository.getUser(username);
    }


    public PreAuthenticatedAuthenticationToken getAuthenticationForLogin(String username, Long id) {
        return new PreAuthenticatedAuthenticationToken(
                username,
                id,
                null);
    }
}
