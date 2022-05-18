package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.MemberMembershipRequestDTO;
import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/memberships")
@RestController
public class MembershipController {

    @Autowired
    private MembershipService membershipService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody MemberMembershipRequestDTO membershipRequestDTO) {
        return new ResponseEntity<>(membershipService.saveMembership(membershipRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(membershipService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                membershipService.findById(id),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid MembershipRequestDTO membershipDTO) {
        return new ResponseEntity<>(membershipService.update(membershipDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        membershipService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
