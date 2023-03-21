package com.acciojob.Library_Management_System.Controller;

import com.acciojob.Library_Management_System.DTO.StudentAddRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentByDeptRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentResponseDto;
import com.acciojob.Library_Management_System.Entity.Student;
import com.acciojob.Library_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentAddRequestDto studentAddRequestDto)
    {
        studentService.addStudent(studentAddRequestDto);
        return "Student has been Added";
    }

    @PutMapping("/update_Email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

}
