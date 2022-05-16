package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.repository.LocationDateRepository;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class LocationDateServiceImpl implements LocationDateService {

    @Autowired
    private LocationDateRepository locationDateRepository;

    @Override
    public LocationDateResponseDTO save(LocationDateRequestDTO requestDTO, Location location) {
        LocationDate toBeSavedLocationDate= ModelMapperUtils.map(requestDTO,LocationDate.class);
        toBeSavedLocationDate.setLocation(location);
        if(toBeSavedLocationDate.getHasTimeSlot()){
            locationDateRepository.save(toBeSavedLocationDate);
        }else{
            locationDateRepository.save(toBeSavedLocationDate);
        }
        return ModelMapperUtils.map(toBeSavedLocationDate,LocationDateResponseDTO.class);

    }
}
