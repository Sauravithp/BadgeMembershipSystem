package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.repository.LocationClosedRepository;
import miu.edu.badgesystem.repository.LocationDateRepository;
import miu.edu.badgesystem.service.LocationClosedService;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationClosedServiceImpl implements LocationClosedService {

    @Autowired
    private LocationClosedRepository locationClosedRepository;


    @Override
    public LocationClosedResponseDTO save(LocationClosedDTO requestDTO, Location location) {
        return null;
    }
}
