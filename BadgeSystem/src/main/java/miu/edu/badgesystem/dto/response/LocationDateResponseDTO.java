package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class LocationDateResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private Boolean hasTimeSlot;

    @NotNull
    private Character status;

    private List<LocationTimeSlotResponseDTO> timeSlots;

    private List<LocationClosedResponseDTO> locationClosed;




}
