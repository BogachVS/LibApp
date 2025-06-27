package com.example.libapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    public AuthorModel author;
    @Column(name = "publication_year")
    public int publicationYear;
    public String genre;

}