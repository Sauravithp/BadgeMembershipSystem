package com.example.persistence;

import com.example.persistence.model.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/badges")
public class BadgeController {

    @Autowired
    private BadgeRepository badgeRepository;

    @PostMapping
    public ResponseEntity<?> save(){
        Badge badge=new Badge();
        badge.setStatus('Y');
        badgeRepository.save(badge);

        return ResponseEntity.ok(badge);
    }
}
