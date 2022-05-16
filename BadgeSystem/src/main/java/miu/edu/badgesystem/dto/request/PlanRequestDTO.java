package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
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

    @NotNull
    private List<Role> roles;

    @NotNull
    private Character status;
}
