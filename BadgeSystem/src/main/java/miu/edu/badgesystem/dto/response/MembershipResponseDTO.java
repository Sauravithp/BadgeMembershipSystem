package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Location;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDTO {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Character status;

    private Location location;
}
