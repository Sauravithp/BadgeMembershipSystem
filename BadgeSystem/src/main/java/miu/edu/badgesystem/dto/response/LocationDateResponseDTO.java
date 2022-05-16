package miu.edu.badgesystem.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import miu.edu.badgesystem.dto.request.LocationTimeSlotDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean hasClosedDate;

    @NotNull
    private Character status;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<LocationTimeSlotResponseDTO> timeSlots;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private LocationClosedResponseDTO locationClosed;




}
