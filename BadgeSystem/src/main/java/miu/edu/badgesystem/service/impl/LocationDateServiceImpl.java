package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.repository.LocationClosedRepository;
import miu.edu.badgesystem.repository.LocationDateRepository;
import miu.edu.badgesystem.service.LocationClosedService;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationDateServiceImpl implements LocationDateService {

    @Autowired
    private LocationDateRepository locationDateRepository;

    @Autowired
    private LocationClosedService locationClosedService;

    @Override
    public LocationDateResponseDTO save(LocationDateRequestDTO requestDTO, Location location) {
        LocationDate toBeSavedLocationDate = ModelMapperUtils.map(requestDTO, LocationDate.class);
        toBeSavedLocationDate.setLocation(location);
        if (toBeSavedLocationDate.getHasTimeSlot()) {
        } else if (toBeSavedLocationDate.getHasLocationClosedDate()) {
            List<LocationClosed> locationClosedList = locationClosedService.save(requestDTO.getClosedDTO());
            toBeSavedLocationDate.setLocationClosed(locationClosedList);
        }
        locationDateRepository.save(toBeSavedLocationDate);
        LocationDateResponseDTO locationDateResponseDTO = getLocationDateResponseDTO(toBeSavedLocationDate);

        return locationDateResponseDTO;

    }

    private LocationDateResponseDTO getLocationDateResponseDTO(LocationDate locationDate) {

       LocationDateResponseDTO locationDateResponseDTO=new LocationDateResponseDTO();
       locationDateResponseDTO.setEndDate(locationDate.getEndDate());
       locationDateResponseDTO.setStartDate(locationDate.getStartDate());
       locationDateResponseDTO.setStatus(locationDate.getStatus());
       locationDateResponseDTO.setEndTime(locationDate.getEndTime());
       locationDateResponseDTO.setStartTime(locationDate.getStartTime());
       locationDateResponseDTO.setHasTimeSlot(locationDate.getHasTimeSlot());
       locationDateResponseDTO.setHasClosedDate(locationDate.getHasLocationClosedDate());
       locationDateResponseDTO.setId(locationDate.getId());
        locationDateResponseDTO.setLocationClosed(getLocationClosedResponseDTO(locationDate));
return locationDateResponseDTO;
    }

    private LocationClosedResponseDTO getLocationClosedResponseDTO(LocationDate locationDate) {

        List<LocationClosed> locationClosed = locationDate.getLocationClosed();
        List<LocalDate> locationDates = new ArrayList<>();

        locationClosed.forEach(closed -> {
            locationDates.add(closed.getDate());
        });

        return LocationClosedResponseDTO.builder().date(locationDates).build();
    }
}
