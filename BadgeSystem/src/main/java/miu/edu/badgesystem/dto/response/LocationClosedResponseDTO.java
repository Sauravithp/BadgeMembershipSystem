package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class LocationClosedResponseDTO {

    @NotNull
    private String weekDays;;

    @NotNull
    private LocalDate date;

}
