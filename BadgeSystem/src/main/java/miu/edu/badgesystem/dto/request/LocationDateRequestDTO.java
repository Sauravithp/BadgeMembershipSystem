package miu.edu.badgesystem.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class LocationDateRequestDTO {

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

    private List<LocationTimeSlotDTO> timeSlots;

    private List<LocationClosedDTO> closedDTO;

}
