package miu.edu.badgesystem.dto.request;

import com.sun.istack.NotNull;
import lombok.*;
import miu.edu.badgesystem.model.LocationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationUpdateRequestDTO {

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

}
