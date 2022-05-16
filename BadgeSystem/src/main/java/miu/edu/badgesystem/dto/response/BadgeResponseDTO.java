package miu.edu.badgesystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeResponseDTO {

    private Long id;

    private String badgeNumber;

    private Character status;

}
