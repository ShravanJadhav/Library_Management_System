package com.acciojob.Library_Management_System.Service;

import com.acciojob.Library_Management_System.DTO.IssueReturnBookRequestDto;
import com.acciojob.Library_Management_System.DTO.IssueReturnBookResponseDto;
import com.acciojob.Library_Management_System.Entity.Book;
import com.acciojob.Library_Management_System.Entity.LibraryCard;
import com.acciojob.Library_Management_System.Entity.Transaction;
import com.acciojob.Library_Management_System.Enum.CardStatus;
import com.acciojob.Library_Management_System.Enum.TransactionStatus;
import com.acciojob.Library_Management_System.Repository.BookRepository;
import com.acciojob.Library_Management_System.Repository.LibraryCardRepository;
import com.acciojob.Library_Management_System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;
    public IssueReturnBookResponseDto isIssueeBook(IssueReturnBookRequestDto issueReturnBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);

        //1. Card
        LibraryCard card;
        try{
             card = libraryCardRepository.findById(issueReturnBookRequestDto.getCardId()).get();

        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Invalid Card ID");
            transactionRepository.save(transaction);
            throw new Exception ("Invalid Card ID");
        }

        Book book;
        try{
            book= bookRepository.findById(issueReturnBookRequestDto.getBookId()).get();

        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Invalid Book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book ID");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        //both card and book valid
        if(card.getCardStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Your card is not Active");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not Active");
        }
        if(book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Sorry..! Book is Already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry..! Book is Already issued");
        }

        //i can issue the book
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getTransactionsList().add(transaction);
        card.getBooksIssuedList().add(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setActionMessage("Transaction has Successfully done");

        libraryCardRepository.save(card); //it will save book and transaction also

        //prepare response dto
        IssueReturnBookResponseDto issueReturnBookResponseDto = new IssueReturnBookResponseDto();
        issueReturnBookResponseDto.setBookName(book.getTitle());
        issueReturnBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueReturnBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);

        // send an email
        String text = "Congrats...!!"+ card.getStudent().getName()+" You have been issued "+book.getTitle()+" book";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciojobshravan@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);

        return issueReturnBookResponseDto;
    }

    public IssueReturnBookResponseDto returnBook(IssueReturnBookRequestDto issueReturnBookRequestDto) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(false);

        LibraryCard card;
        try{
            card = libraryCardRepository.findById(issueReturnBookRequestDto.getCardId()).get();

        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Invalid Card ID");
            transactionRepository.save(transaction);
            throw new Exception ("Invalid Card ID");
        }

        Book book;
        try{
            book= bookRepository.findById(issueReturnBookRequestDto.getBookId()).get();

        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Invalid Book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book ID");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        if(book.isIssued()==false){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setActionMessage("Sorry..! this book is not issues");
            transactionRepository.save(transaction);
            throw new Exception("Sorry..! this book is not issues");
        }

        book.setIssued(false);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getTransactionsList().add(transaction);
        card.getBooksIssuedList().remove(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setActionMessage("Transaction has Successfully done");

        libraryCardRepository.save(card); //it will save book and transaction also

        //prepare response dto
        IssueReturnBookResponseDto issueReturnBookResponseDto = new IssueReturnBookResponseDto();
        issueReturnBookResponseDto.setBookName(book.getTitle());
        issueReturnBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueReturnBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);

        return issueReturnBookResponseDto;
    }

    public String getAllTxns(int cardId) {
        List<Transaction> transactionList = transactionRepository.getAllSuccessfullTxnsWithCardNo(cardId);

        String ans ="";
        for(Transaction t : transactionList){
            ans+= t.getTransactionNumber();
            ans+="\n";
        }
        return ans;
    }
}
