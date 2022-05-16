package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Integer count;

    private Boolean isLimited;

    private List<Role> roles;

    private Character status;
}
