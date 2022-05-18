package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanLocationResponseDTO {


    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String capacity;

    @NotNull
    private Character status;

}
