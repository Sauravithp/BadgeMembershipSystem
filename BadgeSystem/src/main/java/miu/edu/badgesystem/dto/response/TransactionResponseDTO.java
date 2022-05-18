package miu.edu.badgesystem.dto.response;

import lombok.Data;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;

import java.time.LocalDate;

@Data
public class TransactionResponseDTO {
    private Long id;
    private LocalDate date;

    private Character status;

    private String transactionNumber;


}

