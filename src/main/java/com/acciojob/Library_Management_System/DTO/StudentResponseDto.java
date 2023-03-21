package com.acciojob.Library_Management_System.DTO;

import com.acciojob.Library_Management_System.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentResponseDto {
    private int id;
    private String name;
    private String email;
    private Department department;
}
