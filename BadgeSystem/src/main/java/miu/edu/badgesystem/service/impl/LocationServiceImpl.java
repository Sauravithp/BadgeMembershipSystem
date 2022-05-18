package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.LocationRequestDTO;
import miu.edu.badgesystem.dto.request.LocationUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.dto.response.LocationUpdateResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.repository.LocationRepository;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationDateService locationDateService;


    @Override
    public LocationResponseDTO save(LocationRequestDTO requestDTO) {
        Location location = locationRepository.getLocationByName(requestDTO.getName(),
                requestDTO.getLocationType());
        if (Objects.nonNull(location)) {
            throw new DataDuplicationException("Location with name:" + requestDTO.getName() + " already exists");
        }
        Location locationToSave = ModelMapperUtils.map(requestDTO, Location.class);
        locationToSave.setStatus('Y');
        Location savedLocation = locationRepository.save(locationToSave);
        LocationDateResponseDTO locationDate = locationDateService.save(requestDTO.getLocationDateRequestDTO(),
                savedLocation);
        LocationResponseDTO finalResponse = ModelMapperUtils.map(savedLocation,
                LocationResponseDTO.class);
        finalResponse.setLocationDate(locationDate);
        return finalResponse;
    }

    @Override
    public LocationUpdateResponseDTO update(Long id, LocationUpdateRequestDTO updateRequestDTO) {
        Location location = locationRepository.getById(id);
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("Location with name:" + updateRequestDTO.getName() + " not found");
        }
        location.setName(updateRequestDTO.getName());
        location.setDescription(updateRequestDTO.getDescription());
        location.setCapacity(updateRequestDTO.getCapacity());
        location.setStatus(updateRequestDTO.getStatus());
        location.setLocationType(updateRequestDTO.getLocationType());
        LocationUpdateResponseDTO savedLocation = ModelMapperUtils.map(locationRepository.save(location),
                LocationUpdateResponseDTO.class);
        return savedLocation;
    }

    @Override
    public List<LocationResponseDTO> getAllLocation() {
        List<Location> location = locationRepository.getAllLocation();
        List<LocationResponseDTO> responseDTOS = new ArrayList<>();
        if(location.isEmpty()) {
            throw new NoContentFoundException("Location(s) is empty", "No data found in Location");

        }
        location.forEach(loc -> {
            LocationResponseDTO locationResponseDTO=ModelMapperUtils.map(loc, LocationResponseDTO.class);
            LocationDateResponseDTO responseDTO=locationDateService.getLocationDateById(locationResponseDTO.getId());
            locationResponseDTO.setLocationDate(responseDTO);
            responseDTOS.add(locationResponseDTO);
            });

        return responseDTOS;
    }

    @Override
    public LocationResponseDTO getLocationById(Long id) {
        Location location = locationRepository.getLocationByID(id);
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("No location found");
        }
        LocationDateResponseDTO locationDate = locationDateService.getLocationDateById(id);
        LocationResponseDTO finalResponse = ModelMapperUtils.map(location,
                LocationResponseDTO.class);
        finalResponse.setLocationDate(locationDate);
        return finalResponse;
    }

    @Override
    public void delete(Long id) {
        Location location = locationRepository.getLocationByID(id);
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("No location found");
        }
        location.setStatus('D');
        locationDateService.delete(id);
        locationRepository.save(location);
    }
}
