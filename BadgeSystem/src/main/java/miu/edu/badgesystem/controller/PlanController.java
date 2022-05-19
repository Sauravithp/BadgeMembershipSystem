package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.AddRoleRequestDTO;
import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.request.PlanUpdateRequestDTO;
import miu.edu.badgesystem.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/plans")
@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PlanRequestDTO planDTO) {
        return new ResponseEntity(planService.save(planDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(planService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                planService.findById(id),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PlanUpdateRequestDTO planDTO) {
        return new ResponseEntity<>(planService.update(planDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> addRoles(@RequestBody AddRoleRequestDTO addRoleRequestDTO, @PathVariable Long id) {
        planService.addRolesToExistingPlan(addRoleRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/role/{planId}/{roleId}")
    public ResponseEntity<?> removeRole(@PathVariable("planId") Long planId, @PathVariable("roleId") Long roleId) {
        planService.removeRoleFromPlan(planId, roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{planId}/locations")
    public ResponseEntity<?> getListLocationByPlan(@PathVariable("planId") Long planId) {
        return ResponseEntity.ok(planService.getLocationsByPlanId(planId));
    }
}
