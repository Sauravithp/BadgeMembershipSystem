package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.dto.request.*;
import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.repository.LocationClosedRepository;
import miu.edu.badgesystem.service.LocationClosedService;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationService;
import miu.edu.badgesystem.service.LocationTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationTimeSlotService locationTimeSlotService;

    @Autowired
    private LocationClosedService locationClosedService;

    @Autowired
    private LocationDateService locationDateService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated LocationRequestDTO requestDTO){
        return ResponseEntity.ok(locationService.save(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody @Validated LocationUpdateRequestDTO requestDTO){
        return ResponseEntity.ok(locationService.update(id,requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocation(@PathVariable("id") Long id){
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllLocation(){
        List<LocationResponseDTO> locationResponseDTO=locationService.getAllLocation();
        return ResponseEntity.ok(locationResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        locationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/timeslot/{id}")
    public ResponseEntity<?> updateTimeSlot(@PathVariable("id") Long id,
                                            @RequestBody LocationTimeSlotDTO locationTimeSlotDTO){
        return ResponseEntity.ok(locationTimeSlotService.updateTimeSlot(id,locationTimeSlotDTO));
    }

    @PutMapping("/closed/{id}")
    public ResponseEntity<?> updateLocationClosed(@PathVariable("id") Long id,
                                            @RequestBody LocationClosedUpdateRequestDTO locationTimeSlotDTO){
        return ResponseEntity.ok(locationClosedService.updateLocationClosedDate(id,locationTimeSlotDTO));
    }

    @PutMapping("/date/{id}")
    public ResponseEntity<?> updateLocationDate(@PathVariable("id") Long id,
                                                  @RequestBody LocationDateRequestDTO locationDateRequestDTO){
        return ResponseEntity.ok(locationDateService.updateLocationDate(id,locationDateRequestDTO));
    }
}
