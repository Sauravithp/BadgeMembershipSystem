package miu.edu.badgesystem.service.Impl;

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
import miu.edu.badgesystem.util.LocationDateUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        LocationDateResponseDTO locationDateResponseDTO = LocationDateUtil.getLocationDateResponseDTO(toBeSavedLocationDate);

        return locationDateResponseDTO;

    }

    @Override
    public String delete(Long id) {
        LocationDate locationDate=locationDateRepository.getLocationDateByLocationId(id);
        locationDate.setStatus('D');
        locationDate.getLocationClosed().forEach(locationClose -> locationClose.setStatus('D') );
        locationDate.getLocationTimeSlots().forEach(locationTimeSlot -> locationTimeSlot.setStatus('D') );
        locationDateRepository.save(locationDate);
        return "deleted";
    }

    @Override
    public LocationDateResponseDTO getLocationDateById(Long id) {
        LocationDate locationDate=locationDateRepository.getLocationDateByLocationId(id);
        LocationDateResponseDTO locationDateResponseDTO = LocationDateUtil.getLocationDateResponseDTO(locationDate);
        return locationDateResponseDTO;
    }

    @Override
    public LocationDateResponseDTO updateLocationDate(Long id, LocationDateRequestDTO locationDateRequestDTO) {
        LocationDate locationDate=locationDateRepository.getLocationDateById(id);
        locationDate.setStatus(locationDateRequestDTO.getStatus());
        locationDate.setEndDate(locationDateRequestDTO.getEndDate());
        locationDate.setStartDate(locationDate.getStartDate());
        locationDateRepository.save(locationDate);
        return ModelMapperUtils.map(locationDate,LocationDateResponseDTO.class);
    }


}
