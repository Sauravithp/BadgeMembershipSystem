package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.model.Location;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRequestDTO {

    private Long id;

    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private Character status;
    // @NotNull
    private Location location;
}
