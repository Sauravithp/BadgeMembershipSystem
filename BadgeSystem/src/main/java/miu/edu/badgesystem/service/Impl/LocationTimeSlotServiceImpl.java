package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;
import miu.edu.badgesystem.dto.response.LocationTimeSlotResponseDTO;
import miu.edu.badgesystem.model.LocationTimeSlot;
import miu.edu.badgesystem.repository.LocationTimeSlotRepository;
import miu.edu.badgesystem.repository.WeekDaysRepository;
import miu.edu.badgesystem.service.LocationTimeSlotService;
import miu.edu.badgesystem.util.DateUtil;
import miu.edu.badgesystem.util.LocationTimeSlotUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationTimeSlotServiceImpl implements LocationTimeSlotService {

    @Autowired
    private LocationTimeSlotRepository locationTimeSlotRepository;

    @Autowired
    private WeekDaysRepository weekDaysRepository;

    @Override
    public List<LocationTimeSlot> save(List<LocationTimeSlotDTO> requestDTO) {
        List<LocationTimeSlot> locationTimeSlotList=new ArrayList<>();
        requestDTO.forEach(request->{
           LocationTimeSlot locationTimeSlot= LocationTimeSlotUtil.mapToLocationTimeSlot(request);
            locationTimeSlot.setWeekdays(weekDaysRepository
                    .getWeekDaysByName(DateUtil.getDayName(locationTimeSlot.getDate())));
            locationTimeSlotList.add(locationTimeSlot);
        });
        return locationTimeSlotList;
    }

    @Override
    public LocationTimeSlotResponseDTO updateTimeSlot(Long id, LocationTimeSlotDTO requestDTO) {
        LocationTimeSlot locationTimeSlot=locationTimeSlotRepository.getLocationTimeSlotById(id);
        LocationTimeSlotUtil.mapToLocationTimeSlot(locationTimeSlot,requestDTO);
        locationTimeSlot.setWeekdays(weekDaysRepository
                .getWeekDaysByName(DateUtil.getDayName(requestDTO.getDate())));
        locationTimeSlotRepository.save(locationTimeSlot);
        return ModelMapperUtils.map(locationTimeSlot,LocationTimeSlotResponseDTO.class);
    }
}
