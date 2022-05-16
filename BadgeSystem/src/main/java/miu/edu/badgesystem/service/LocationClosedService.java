package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.LocationClosedDTO;
import miu.edu.badgesystem.dto.request.LocationDateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;

import java.util.List;

public interface LocationClosedService {

    List<LocationClosed> save(LocationClosedDTO requestDTO);

}
