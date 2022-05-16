package miu.edu.badgesystem.service.impl;

import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;
import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationTimeSlot;
import miu.edu.badgesystem.repository.WeekDaysRepository;
import miu.edu.badgesystem.service.LocationTimeSlotService;
import miu.edu.badgesystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationTimeSlotServiceImpl implements LocationTimeSlotService {

    @Autowired
    private WeekDaysRepository weekDaysRepository;

    @Override
    public List<LocationTimeSlot> save(List<LocationTimeSlotDTO> requestDTO) {
        List<LocationTimeSlot> locationTimeSlotList=new ArrayList<>();
        requestDTO.forEach(request->{
            LocationTimeSlot locationTimeSlot=new LocationTimeSlot();
            locationTimeSlot.setDate(request.getDate());
            locationTimeSlot.setEndTime(request.getEndTime());
            locationTimeSlot.setStartTime(request.getStartTime());
            locationTimeSlot.setStatus('Y');
            locationTimeSlot.setWeekdays(weekDaysRepository
                    .getWeekDaysByName(DateUtil.getDayName(locationTimeSlot.getDate())));
            locationTimeSlotList.add(locationTimeSlot);
        });
        return locationTimeSlotList;    }
}
