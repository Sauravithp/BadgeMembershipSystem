package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(badgeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                badgeService.findById(id),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        badgeService.deleteBadge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
