package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.LocationRequestDTO;
import miu.edu.badgesystem.dto.response.LocationResponseDTO;

import java.util.List;

public interface LocationService {

    LocationResponseDTO save(LocationRequestDTO requestDTO);

    LocationResponseDTO update(Long id,LocationRequestDTO updateRequestDTO);

    List<LocationResponseDTO> getAllLocation();

    LocationResponseDTO getLocationById(Long id);

    void delete(Long id);


}
