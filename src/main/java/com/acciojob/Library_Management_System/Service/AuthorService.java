package com.acciojob.Library_Management_System.Service;

import com.acciojob.Library_Management_System.DTO.AuthorAddRequestDto;
import com.acciojob.Library_Management_System.Entity.Author;
import com.acciojob.Library_Management_System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public void addAuthor(AuthorAddRequestDto authorAddRequestDto) {

         Author author = new Author();
         author.setName(authorAddRequestDto.getName());
        author.setAge(authorAddRequestDto.getAge());
        author.setEmail(authorAddRequestDto.getEmail());
        author.setMobile(authorAddRequestDto.getMobile());

        authorRepository.save(author);
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }
}
