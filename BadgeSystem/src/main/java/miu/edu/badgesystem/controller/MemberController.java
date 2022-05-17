package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.service.TransactionService;
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

    @Autowired
    private TransactionService transactionService;

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


    @GetMapping("/{id}/plans")
    public ResponseEntity<?> findMemberPlans(@PathVariable Long id) {
        return new ResponseEntity<>(
                memberService.findMemberPlans(id),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/memberships")
    public ResponseEntity<?> findMemberMemberships(@PathVariable Long id) {
        return new ResponseEntity<>(
                memberService.findMemberMemberships(id),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<?> findMemberTransactions(@PathVariable Long id) {
        return new ResponseEntity<>(
                 transactionService.getTransactionByMembershipId(id),
                HttpStatus.OK);
    }
}
