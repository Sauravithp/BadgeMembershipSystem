package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public void login(@RequestBody LoginRequestDTO loginRequestDTO){

        System.out.println(loginRequestDTO.getUsername());
        System.out.println(loginRequestDTO.getPassword());
    }
}
