package miu.edu.badgesystem.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class LocationClosedDTO {

    @NotNull
    private String weekDays;;

    @NotNull
    private List<LocalDate> date;

}