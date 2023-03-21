package com.acciojob.Library_Management_System.DTO;

import com.acciojob.Library_Management_System.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class IssueReturnBookResponseDto {
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;

}
