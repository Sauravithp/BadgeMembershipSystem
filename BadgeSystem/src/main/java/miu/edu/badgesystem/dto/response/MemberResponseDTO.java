package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {


    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private Character status;

    private String badgeNumber;

    private List<Role> roles;

}
