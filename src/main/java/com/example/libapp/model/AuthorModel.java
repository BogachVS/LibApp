package com.example.libapp.model;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String name;
    @Column(name = "birth_year")
    public int birthYear;
}