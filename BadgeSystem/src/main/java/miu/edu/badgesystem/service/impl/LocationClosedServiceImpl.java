package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.service.LocationClosedService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
