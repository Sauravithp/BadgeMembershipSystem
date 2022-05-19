package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.dto.request.LocationClosedUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.model.LocationClosed;

import java.util.List;

public interface LocationClosedService {

    List<LocationClosed> save(LocationClosedDTO requestDTO);

    LocationClosedResponseDTO updateLocationClosedDate(Long id, LocationClosedUpdateRequestDTO requestDTO);

}
