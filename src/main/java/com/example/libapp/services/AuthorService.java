package com.example.libapp.services;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.repository.models.AuthorModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthorService
{
    private final AuthorRepository authorRepository;

    public void AddAuthor(AuthorDTO body)
    {
        AuthorModel author = new AuthorModel();

        if(body.getName()!=null && !body.getName().isEmpty())
            author.setName(body.getName());
        else
            throw new IllegalArgumentException("Name cannot be empty");

        if(body.getBirth_year() > 0)
            author.setBirth_year(body.getBirth_year());
        else
            throw new IllegalArgumentException("Birth year isn't a valid year");

        authorRepository.save(author);
    }

    public Page<AuthorModel> GetAuthorsList(Pageable pageable)
    {
        return authorRepository.findAll(pageable);
    }

    public AuthorModel GetAuthor(Long id)
    {
        return authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author Not Found"));
    }
}