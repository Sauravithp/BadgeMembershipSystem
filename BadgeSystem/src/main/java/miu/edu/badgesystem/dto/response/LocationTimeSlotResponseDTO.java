package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LocationTimeSlotResponseDTO {

    @NotNull
    private Long id;

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
