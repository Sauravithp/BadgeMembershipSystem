package miu.edu.badgesystem.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import miu.edu.badgesystem.model.LocationType;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocationTimeSlotDTO {

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private LocalDate date;

    @NotNull
    private Character status;

}
