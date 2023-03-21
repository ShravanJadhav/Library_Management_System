package com.acciojob.Library_Management_System.Service;

import com.acciojob.Library_Management_System.DTO.StudentAddRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentByDeptRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
import com.acciojob.Library_Management_System.DTO.StudentResponseDto;
import com.acciojob.Library_Management_System.Entity.LibraryCard;
import com.acciojob.Library_Management_System.Entity.Student;
import com.acciojob.Library_Management_System.Enum.CardStatus;
import com.acciojob.Library_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(StudentAddRequestDto studentAddRequestDto)
    {

        Student student = new Student();
        student.setName(studentAddRequestDto.getName());
        student.setEmail(studentAddRequestDto.getEmail());
        student.setDepartment(studentAddRequestDto.getDepartment());
        student.setAge(studentAddRequestDto.getAge());

        //create Library card
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student); // will save student and card
    }

    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //update step
        Student updatedStudent = studentRepository.save(student);

        //convert to responseStudent
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());
        studentResponseDto.setDepartment(updatedStudent.getDepartment());

        return studentResponseDto;
    }
}