package miu.edu.transactionmanagementsystem.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionResponseDTO {
    private Long id;
    private LocalDate date;

    private Character status;

}

