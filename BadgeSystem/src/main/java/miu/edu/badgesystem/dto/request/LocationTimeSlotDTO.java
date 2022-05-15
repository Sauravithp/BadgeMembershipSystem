package miu.edu.badgesystem.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import miu.edu.badgesystem.model.LocationType;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class LocationTimeSlotDTO {

    @NotNull
    private String name;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private LocalDate date;

    @NotNull
    private Character status;

}
