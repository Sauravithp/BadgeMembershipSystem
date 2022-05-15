package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class LoginController {

    @PostMapping
    public void login(LoginRequestDTO loginRequestDTO){

    }
}
