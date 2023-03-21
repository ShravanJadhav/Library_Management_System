package com.acciojob.Library_Management_System.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthorAddRequestDto {
    private String name;
    private int age;
    private String mobile;

    private String email;
}
