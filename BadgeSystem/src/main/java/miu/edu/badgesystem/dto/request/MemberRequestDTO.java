package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Membership;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String emailAddress;

    @NotNull
    private Character status;

    private List<Membership> memberships;

  //  @NotEmpty
    private List<MemberRoles> memberRoles;

   // @NotEmpty
    private List<Badge> badges;

}
