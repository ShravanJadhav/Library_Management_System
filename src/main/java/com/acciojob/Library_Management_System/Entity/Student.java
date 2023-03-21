package com.acciojob.Library_Management_System.Entity;

import com.acciojob.Library_Management_System.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
     private Department department;

    private int age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard Card;

}
