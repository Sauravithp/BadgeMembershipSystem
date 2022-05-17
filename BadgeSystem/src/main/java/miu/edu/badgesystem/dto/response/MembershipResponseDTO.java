package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Plan;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Character status;

    private Location location;

    private Plan plan;
}
