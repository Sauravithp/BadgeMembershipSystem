package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationTimeSlotResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private LocalDate date;

    @NotNull
    private Character status;

}
