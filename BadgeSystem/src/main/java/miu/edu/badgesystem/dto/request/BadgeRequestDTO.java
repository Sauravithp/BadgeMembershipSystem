package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeRequestDTO {

    private Long id;

    @NotNull
    private String badgeNumber;

    @NotNull
    private Character status;

}
