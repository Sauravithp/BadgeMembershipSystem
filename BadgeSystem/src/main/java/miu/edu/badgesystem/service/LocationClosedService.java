package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;

public interface LocationClosedService {


    LocationClosedResponseDTO save(LocationClosedDTO requestDTO, Location location);


}
