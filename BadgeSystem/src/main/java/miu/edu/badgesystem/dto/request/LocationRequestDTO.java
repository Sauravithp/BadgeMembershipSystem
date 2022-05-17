package miu.edu.badgesystem.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import miu.edu.badgesystem.model.enums.LocationType;

@Getter
@Setter
@Builder
public class LocationRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer capacity;

    @NotNull
    private Character status;

    @NotNull
    private LocationType locationType;

    private LocationDateRequestDTO locationDateRequestDTO;

}
