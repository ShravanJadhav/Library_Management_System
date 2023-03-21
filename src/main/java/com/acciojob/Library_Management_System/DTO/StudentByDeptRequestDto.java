package com.acciojob.Library_Management_System.DTO;

import com.acciojob.Library_Management_System.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentByDeptRequestDto {
    private Department department;
}
