package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Character status;

}
