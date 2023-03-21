package com.acciojob.Library_Management_System.DTO;

import com.acciojob.Library_Management_System.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookAddRequestDto {
    private String title;
    private int price;
    private Genre genre;
    private int authorId;

}
