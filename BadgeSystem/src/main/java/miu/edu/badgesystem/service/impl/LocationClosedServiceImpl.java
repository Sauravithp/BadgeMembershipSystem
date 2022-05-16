package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationClosedDTO;
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
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationClosedServiceImpl implements LocationClosedService {

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
}
