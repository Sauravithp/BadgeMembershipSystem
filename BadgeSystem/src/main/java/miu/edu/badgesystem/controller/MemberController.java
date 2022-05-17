package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.BadgeRequestDTO;
import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MemberRequestDTO memberDTO) {
        return new ResponseEntity(memberService.save(memberDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                memberService.findById(id),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid MemberUpdateRequestDTO memberDTO) {
        return new ResponseEntity<>(memberService.update(memberDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createBadgeForAMember(@RequestBody BadgeRequestDTO badgeDto, @PathVariable Long id) {
        return new ResponseEntity<>(memberService.createBadgeForAMember(badgeDto, id), HttpStatus.OK);
    }

    @GetMapping("/memberships/{badgeNumber}")
    public ResponseEntity<?> getMembershipByBadgeNumber(@PathVariable String badgeNumber) {
        return new ResponseEntity<>(
                memberService.getMembershipsByBadgeNumber(badgeNumber),
                HttpStatus.OK);
    }
}
