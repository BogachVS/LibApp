package com.example.libapp.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO
{
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s]+$")
    @Size(min = 3, max = 45)
    public String name;
    @NotNull
    @Min(1400)
    @Max(2019)
    public Integer birthYear;
}