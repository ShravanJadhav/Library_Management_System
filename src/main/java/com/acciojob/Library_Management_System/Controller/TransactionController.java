package com.acciojob.Library_Management_System.Controller;

import com.acciojob.Library_Management_System.DTO.IssueReturnBookRequestDto;
import com.acciojob.Library_Management_System.DTO.IssueReturnBookResponseDto;
import com.acciojob.Library_Management_System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue")
    public ResponseEntity isIssueBook(@RequestBody IssueReturnBookRequestDto issueReturnBookRequestDto){
        IssueReturnBookResponseDto issueReturnBookResponseDto;
        try{
            issueReturnBookResponseDto = transactionService.isIssueeBook(issueReturnBookRequestDto);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(issueReturnBookResponseDto,HttpStatus.ACCEPTED);
    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody IssueReturnBookRequestDto issueReturnBookRequestDto){
        IssueReturnBookResponseDto issueReturnBookResponseDto;
        try{
            issueReturnBookResponseDto = transactionService.returnBook(issueReturnBookRequestDto);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(issueReturnBookResponseDto,HttpStatus.ACCEPTED);
    }

}
