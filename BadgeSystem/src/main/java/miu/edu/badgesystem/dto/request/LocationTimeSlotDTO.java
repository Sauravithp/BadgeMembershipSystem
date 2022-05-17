package miu.edu.badgesystem.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDate;

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
