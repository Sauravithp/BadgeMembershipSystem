package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.LocationRequestDTO;
import miu.edu.badgesystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated LocationRequestDTO requestDTO){
        return ResponseEntity.ok(locationService.save(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody @Validated LocationRequestDTO requestDTO){
        return ResponseEntity.ok(locationService.update(id,requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocation(@PathVariable("id") Long id){
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllLocation(){
        return ResponseEntity.ok(locationService.getAllLocation());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        locationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
