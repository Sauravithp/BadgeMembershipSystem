package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.LocationRequestDTO;
import miu.edu.badgesystem.dto.request.LocationUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.dto.response.LocationUpdateResponseDTO;

import java.util.List;

public interface LocationService {

    LocationResponseDTO save(LocationRequestDTO requestDTO);

    LocationUpdateResponseDTO update(Long id, LocationUpdateRequestDTO requestDTO);

    List<LocationResponseDTO> getAllLocation();

    LocationResponseDTO getLocationById(Long id);

    void delete(Long id);


}
