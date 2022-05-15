package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.UserRequestDTO;
import miu.edu.badgesystem.model.User;
import miu.edu.badgesystem.repository.UserRepository;
import miu.edu.badgesystem.service.UserService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(UserRequestDTO userRequestDTO) {
       User user= ModelMapperUtils.map(userRequestDTO,User.class);
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
}
