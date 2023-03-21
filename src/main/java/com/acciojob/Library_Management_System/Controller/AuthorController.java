package com.acciojob.Library_Management_System.Controller;

import com.acciojob.Library_Management_System.DTO.AuthorAddRequestDto;
import com.acciojob.Library_Management_System.Entity.Author;
import com.acciojob.Library_Management_System.Service.AuthorService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorAddRequestDto authorAddRequestDto){
        authorService.addAuthor(authorAddRequestDto);
        return "Author has been Added";
    }

    @GetMapping("/get_authors")
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthor();

    }
}
