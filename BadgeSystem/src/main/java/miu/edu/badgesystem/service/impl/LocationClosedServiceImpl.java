package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.dto.request.LocationClosedUpdateRequestDTO;
import miu.edu.badgesystem.dto.request.LocationUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationTimeSlotResponseDTO;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationTimeSlot;
import miu.edu.badgesystem.repository.LocationClosedRepository;
import miu.edu.badgesystem.service.LocationClosedService;
import miu.edu.badgesystem.util.DateUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationClosedServiceImpl implements LocationClosedService {

    @Autowired
    private LocationClosedRepository locationClosedRepository;

    @Override
    public List<LocationClosed> save(LocationClosedDTO requestDTO) {

        List<LocationClosed> locationClosedList=new ArrayList<>();
        requestDTO.getDate().forEach(request->{
            LocationClosed locationClosed=new LocationClosed();
            locationClosed.setDate(request);
            locationClosed.setStatus('Y');
            locationClosedList.add(locationClosed);
        });
        return locationClosedList;
    }

    @Override
    public LocationClosedResponseDTO updateLocationClosedDate(Long id, LocationClosedUpdateRequestDTO requestDTO) {
        LocationClosed locationClosed=locationClosedRepository.getLocationClosedById(id);
        locationClosed.setStatus(requestDTO.getStatus());
        locationClosed.setDate(requestDTO.getDate());
        locationClosedRepository.save(locationClosed);

        return ModelMapperUtils.map(locationClosed, LocationClosedResponseDTO.class);    }
}
