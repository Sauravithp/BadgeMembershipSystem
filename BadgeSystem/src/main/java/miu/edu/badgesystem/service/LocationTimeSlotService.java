package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;
import miu.edu.badgesystem.dto.response.LocationTimeSlotResponseDTO;
import miu.edu.badgesystem.model.LocationTimeSlot;

import java.util.List;

public interface LocationTimeSlotService {

    List<LocationTimeSlot> save(List<LocationTimeSlotDTO> requestDTO);

    LocationTimeSlotResponseDTO updateTimeSlot(Long id, LocationTimeSlotDTO requestDTO);

}
