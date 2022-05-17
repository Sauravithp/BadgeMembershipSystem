package miu.edu.badgesystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDTO {

    private LocalDate date;

    private Long membershipId;

    private Long locationId;


}
