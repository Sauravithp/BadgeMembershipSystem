package miu.edu.badgesystem.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private Long id;
    private Date dateTime;

}

