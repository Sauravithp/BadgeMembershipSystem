package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Plan;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRequestDTO {

    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private Character status;

    @NotNull
    private Long location;

    @NotNull
    private Long plan;

}
