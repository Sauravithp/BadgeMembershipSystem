package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.model.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
