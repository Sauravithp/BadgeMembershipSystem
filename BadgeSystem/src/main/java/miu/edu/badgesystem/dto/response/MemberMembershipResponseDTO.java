package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Plan;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMembershipResponseDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Character status;

    private Location location;

    private Plan plan;
}
