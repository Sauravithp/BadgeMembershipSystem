package miu.edu.badgesystem.dto.response;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationClosedResponseDTO {

    private LocalDate date;

    private Long id;

    private Character status;

}
