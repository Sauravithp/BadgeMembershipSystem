package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;

public interface LocationDateService {

    LocationDateResponseDTO save(LocationDateRequestDTO requestDTO, Location location);

    String delete(Long id);

    LocationDateResponseDTO getLocationDateById(Long id);

    LocationDateResponseDTO updateLocationDate(Long id, LocationDateRequestDTO locationDateRequestDTO);
}
