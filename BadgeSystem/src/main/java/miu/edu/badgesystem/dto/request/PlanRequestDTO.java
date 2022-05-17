package miu.edu.badgesystem.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import miu.edu.badgesystem.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer count;

    @NotNull
    private Boolean isLimited;

    private List<Long> rolesId;

    private Character status;
}
