package miu.edu.badgesystem.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import miu.edu.badgesystem.dto.request.LocationRequestDTO;
import miu.edu.badgesystem.dto.response.LocationDateResponseDTO;
import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.repository.LocationRepository;
import miu.edu.badgesystem.service.LocationDateService;
import miu.edu.badgesystem.service.LocationService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationDateService locationDateService;


    @Override
    public LocationResponseDTO save(LocationRequestDTO requestDTO) {
        Location location = locationRepository.getLocationByName(requestDTO.getName());
        if (Objects.nonNull(location)) {
            throw new DuplicateRequestException("Location with name:" + requestDTO.getName() + "already exists");
        }
        Location locationToSave = ModelMapperUtils.map(requestDTO, Location.class);
        locationToSave.setStatus('Y');
        Location savedLocation = locationRepository.save(locationToSave);
        LocationDateResponseDTO locationDate = locationDateService.save(requestDTO.getLocationDateRequestDTO(), savedLocation);
        LocationResponseDTO finalResponse = ModelMapperUtils.map(savedLocation,
                LocationResponseDTO.class);
        finalResponse.setLocationDate(locationDate);
        return finalResponse;
    }

    @Override
    public LocationResponseDTO update(Long id, LocationRequestDTO updateRequestDTO) {
        Location location = locationRepository.getLocationByName(updateRequestDTO.getName());
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("Location with name:" + updateRequestDTO.getName() + " not found");
        }
        location.setName(updateRequestDTO.getName());
        location.setDescription(updateRequestDTO.getDescription());
        location.setCapacity(updateRequestDTO.getCapacity());
        location.setStatus(updateRequestDTO.getStatus());
        location.setLocationType(updateRequestDTO.getLocationType());
        LocationResponseDTO savedLocation = ModelMapperUtils.map(locationRepository.save(location),
                LocationResponseDTO.class);
        return savedLocation;
    }

    @Override
    public List<LocationResponseDTO> getAllLocation() {
        List<Location> location = locationRepository.getAllLocation();
        List<LocationResponseDTO> responseDTOS = new ArrayList<>();
        try {
            location.forEach(loc -> {
                responseDTOS.add(ModelMapperUtils.map(loc, LocationResponseDTO.class));
            });
        } catch (Exception ex) {
            throw new NoContentFoundException("Location(s) is empty", "No data found in Location");
        }

        return responseDTOS;
    }

    @Override
    public LocationResponseDTO getLocationById(Long id) {
        Location location = locationRepository.getById(id);
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("No location found");
        }
        return ModelMapperUtils.map(location, LocationResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        Location location = locationRepository.getById(id);
        if (Objects.isNull(location)) {
            throw new NoContentFoundException("No location found");
        }
        location.setStatus('D');
        locationDateService.delete(id);
        locationRepository.save(location);
    }
}
