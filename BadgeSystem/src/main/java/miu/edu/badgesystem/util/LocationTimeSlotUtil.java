package miu.edu.badgesystem.util;

import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;
import miu.edu.badgesystem.model.LocationTimeSlot;

public class LocationTimeSlotUtil {

    public static LocationTimeSlot mapToLocationTimeSlot(LocationTimeSlotDTO locationTimeSlotDTO){
        LocationTimeSlot locationTimeSlot=new LocationTimeSlot();
        locationTimeSlot.setDate(locationTimeSlotDTO.getDate());
        locationTimeSlot.setEndTime(locationTimeSlotDTO.getEndTime());
        locationTimeSlot.setStartTime(locationTimeSlotDTO.getStartTime());
        locationTimeSlot.setStatus('Y');

        return locationTimeSlot;
    }

    public static LocationTimeSlot mapToLocationTimeSlot(LocationTimeSlot locationTimeSlot,
                                                         LocationTimeSlotDTO locationTimeSlotDTO){
        locationTimeSlot.setDate(locationTimeSlotDTO.getDate());
        locationTimeSlot.setEndTime(locationTimeSlotDTO.getEndTime());
        locationTimeSlot.setStartTime(locationTimeSlotDTO.getStartTime());
        locationTimeSlot.setStatus('Y');

        return locationTimeSlot;
    }


}
