package com.example.libapp.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateDTO
{
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9\\s]+$")
    @Size(min = 3, max = 30)
    public String title;
    @Min(1)
    public Long authorId;
    @Min(1456)
    @Max(2025)
    public int publicationYear;
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s]+$")
    @Size(min = 3, max = 46)
    public String genre;
}
