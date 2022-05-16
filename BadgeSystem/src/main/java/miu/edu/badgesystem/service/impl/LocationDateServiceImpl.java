package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.dto.response.LocationTimeSlotResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.LocationTimeSlot;
import miu.edu.badgesystem.repository.LocationDateRepository;
import miu.edu.badgesystem.service.LocationClosedService;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationTimeSlotService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationDateServiceImpl implements LocationDateService {

    @Autowired
    private LocationDateRepository locationDateRepository;

    @Autowired
    private LocationClosedService locationClosedService;

    @Autowired
    private LocationTimeSlotService locationTimeSlotService;


    @Override
    public LocationDateResponseDTO save(LocationDateRequestDTO requestDTO, Location location) {
        LocationDate toBeSavedLocationDate = ModelMapperUtils.map(requestDTO, LocationDate.class);
        toBeSavedLocationDate.setLocation(location);
        if (toBeSavedLocationDate.getHasTimeSlot()) {
            List<LocationTimeSlot> timeSlots=locationTimeSlotService.save(requestDTO.getTimeSlots());
            toBeSavedLocationDate.setLocationTimeSlots(timeSlots);
        }
        if (toBeSavedLocationDate.getHasLocationClosedDate()) {
            List<LocationClosed> locationClosedList = locationClosedService.save(requestDTO.getClosedDTO());
            toBeSavedLocationDate.setLocationClosed(locationClosedList);
        }
        locationDateRepository.save(toBeSavedLocationDate);
        LocationDateResponseDTO locationDateResponseDTO = getLocationDateResponseDTO(toBeSavedLocationDate);

        return locationDateResponseDTO;

    }

    private LocationDateResponseDTO getLocationDateResponseDTO(LocationDate locationDate) {

        LocationDateResponseDTO locationDateResponseDTO = new LocationDateResponseDTO();
        locationDateResponseDTO.setEndDate(locationDate.getEndDate());
        locationDateResponseDTO.setStartDate(locationDate.getStartDate());
        locationDateResponseDTO.setStatus(locationDate.getStatus());
        locationDateResponseDTO.setEndTime(locationDate.getEndTime());
        locationDateResponseDTO.setStartTime(locationDate.getStartTime());
        locationDateResponseDTO.setHasTimeSlot(locationDate.getHasTimeSlot());
        locationDateResponseDTO.setHasClosedDate(locationDate.getHasLocationClosedDate());
        locationDateResponseDTO.setId(locationDate.getId());
        locationDateResponseDTO.setLocationClosed(getLocationClosedResponseDTO(locationDate));
        locationDateResponseDTO.setTimeSlots(getTimeSlotResponse(locationDate));
        return locationDateResponseDTO;
    }

    private LocationClosedResponseDTO getLocationClosedResponseDTO(LocationDate locationDate) {

        if(!locationDate.getHasLocationClosedDate()) {
            return null;
        }else{
            List<LocationClosed> locationClosed = locationDate.getLocationClosed();
            List<LocalDate> locationDates = new ArrayList<>();

            locationClosed.forEach(closed -> {
                locationDates.add(closed.getDate());
            });

            return LocationClosedResponseDTO.builder().date(locationDates).build();
        }

    }

    private List<LocationTimeSlotResponseDTO> getTimeSlotResponse(LocationDate locationDate) {

        if(!locationDate.getHasTimeSlot()) {
            return null;
        }else{
            List<LocationTimeSlot> locationClosed = locationDate.getLocationTimeSlots();
            List<LocationTimeSlotResponseDTO> timeSlotResponse = new ArrayList<>();

            locationClosed.forEach(closed -> {
                LocationTimeSlotResponseDTO responseDTO=new LocationTimeSlotResponseDTO();
                responseDTO.setId(closed.getId());
                responseDTO.setDate(closed.getDate());
                responseDTO.setEndTime(closed.getEndTime());
                responseDTO.setStartTime(closed.getStartTime());
                responseDTO.setStatus(closed.getStatus());
                timeSlotResponse.add(responseDTO);
            });
            return timeSlotResponse;

        }


    }
}
