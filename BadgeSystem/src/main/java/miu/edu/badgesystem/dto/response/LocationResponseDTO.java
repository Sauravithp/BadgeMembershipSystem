package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String capacity;

    @NotNull
    private Character status;

    private LocationDateResponseDTO locationDate;

}
