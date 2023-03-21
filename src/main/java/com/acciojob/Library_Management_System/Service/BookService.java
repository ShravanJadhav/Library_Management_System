package com.acciojob.Library_Management_System.Service;

import com.acciojob.Library_Management_System.DTO.BookAddRequestDto;
import com.acciojob.Library_Management_System.DTO.BookResponseDto;
import com.acciojob.Library_Management_System.Entity.Author;
import com.acciojob.Library_Management_System.Entity.Book;
import com.acciojob.Library_Management_System.Repository.AuthorRepository;
import com.acciojob.Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public BookResponseDto addbook(BookAddRequestDto bookAddRequestDto)
    {
        Book book = new Book();
        book.setTitle(bookAddRequestDto.getTitle());
        book.setGenre(bookAddRequestDto.getGenre());
        book.setPrice(bookAddRequestDto.getPrice());
        book.setIssued(false);

        Author author = authorRepository.findById(bookAddRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author); //will save both book and author

        //create a response also
         BookResponseDto bookResponseDto = new BookResponseDto();
         bookResponseDto.setTitle(book.getTitle());
         bookResponseDto.setPrice(book.getPrice());
         bookResponseDto.setGenre(book.getGenre());

         return bookResponseDto;
    }
}
