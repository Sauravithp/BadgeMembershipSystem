package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMembershipRequestDTO {

    @NotNull
    private Long memberId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Character status;

    @NotNull
    private Long location;

    @NotNull
    private Long plan;

    @NotNull
    private Long role;
}
