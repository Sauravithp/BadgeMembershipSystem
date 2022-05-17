package miu.edu.badgesystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanUpdateRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer count;

    @NotNull
    private Boolean isLimited;

    private Character status;
}
