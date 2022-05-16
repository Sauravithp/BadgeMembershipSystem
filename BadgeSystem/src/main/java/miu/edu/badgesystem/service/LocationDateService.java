package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;

public interface LocationDateService {


    LocationDateResponseDTO save(LocationDateRequestDTO requestDTO, Location location);


}
