package miu.edu.badgesystem.util;

import miu.edu.badgesystem.dto.response.LocationClosedResponseDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.dto.response.LocationTimeSlotResponseDTO;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.LocationTimeSlot;

import java.util.ArrayList;
import java.util.List;

public class LocationDateUtil {

    public static LocationDateResponseDTO getLocationDateResponseDTO(LocationDate locationDate) {

        LocationDateResponseDTO locationDateResponseDTO = new LocationDateResponseDTO();
        locationDateResponseDTO.setEndDate(locationDate.getEndDate());
        locationDateResponseDTO.setStartDate(locationDate.getStartDate());
        locationDateResponseDTO.setStatus(locationDate.getStatus());
        locationDateResponseDTO.setEndTime(locationDate.getEndTime());
        locationDateResponseDTO.setStartTime(locationDate.getStartTime());
        locationDateResponseDTO.setHasTimeSlot(locationDate.getHasTimeSlot());
        locationDateResponseDTO.setHasClosedDate(locationDate.getHasLocationClosedDate());
        locationDateResponseDTO.setId(locationDate.getId());
        locationDateResponseDTO.setLocationClosed(getLocationClosedResponseDTO(locationDate));
        locationDateResponseDTO.setTimeSlots(getTimeSlotResponse(locationDate));
        return locationDateResponseDTO;
    }

    public static List<LocationClosedResponseDTO> getLocationClosedResponseDTO(LocationDate locationDate) {

        if(!locationDate.getHasLocationClosedDate()) {
            return null;
        }else{
            List<LocationClosed> locationClosed = locationDate.getLocationClosed();
            List<LocationClosedResponseDTO> locationDates = new ArrayList<>();

            locationClosed.forEach(closed -> {
                LocationClosedResponseDTO locationClosedResponseDTO=ModelMapperUtils
                        .map(closed,LocationClosedResponseDTO.class);
                locationDates.add(locationClosedResponseDTO);
            });

            return locationDates;
        }

    }

public static List<LocationTimeSlotResponseDTO> getTimeSlotResponse(LocationDate locationDate) {

        if(!locationDate.getHasTimeSlot()) {
            return null;
        }else{
            List<LocationTimeSlot> locationClosed = locationDate.getLocationTimeSlots();
            List<LocationTimeSlotResponseDTO> timeSlotResponse = new ArrayList<>();

            locationClosed.forEach(closed -> {
                LocationTimeSlotResponseDTO responseDTO=new LocationTimeSlotResponseDTO();
                responseDTO.setId(closed.getId());
                responseDTO.setDate(closed.getDate());
                responseDTO.setEndTime(closed.getEndTime());
                responseDTO.setStartTime(closed.getStartTime());
                responseDTO.setStatus(closed.getStatus());
                timeSlotResponse.add(responseDTO);
            });
            return timeSlotResponse;

        }


    }
}
