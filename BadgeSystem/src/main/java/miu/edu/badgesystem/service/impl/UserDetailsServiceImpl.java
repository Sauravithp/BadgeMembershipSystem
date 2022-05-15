package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.model.CustomUserDetails;
import miu.edu.badgesystem.model.User;
import miu.edu.badgesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        return new CustomUserDetails(user);
    }

    private User getUserByName(String username){
       return userRepository.getUser(username);
    }
}
