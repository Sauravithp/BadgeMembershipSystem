package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.LoginRequestDTO;
import miu.edu.badgesystem.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDTO){
        String s=authenticateService.loginUser(requestDTO);
        System.out.println(s);
        System.out.println(requestDTO.getUsername());
        System.out.println(requestDTO.getPassword());
        return ResponseEntity.ok(s);
    }
}
