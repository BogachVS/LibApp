package com.example.libapp.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO
{
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9\\s]+$")
    @Size(min = 3, max = 30)
    public String title;
    @NotNull
    public Long authorId;
    @NotNull
    @Min(1456)
    @Max(2025)
    public Integer publicationYear;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s]+$")
    @Size(min = 3, max = 46)
    public String genre;
}