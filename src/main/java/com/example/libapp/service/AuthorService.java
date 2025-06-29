package com.example.libapp.service;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.mapper.AuthorMapper;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.model.AuthorModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService
{
    private final AuthorRepository authorRepository;

    public void addAuthor(AuthorDTO body)
    {
        AuthorModel author = AuthorMapper.INSTANCE.authorDTOToAuthorModel(body);
        authorRepository.save(author);
    }

    public Page<AuthorDTO> getAuthorsList()
    {
        Pageable paging = PageRequest.of(0,10);
        List<AuthorDTO> authors = AuthorMapper.INSTANCE.authorModelToAuthorDTOList(authorRepository.findAll());
        return new PageImpl<>(authors,paging,authors.size());
    }

    public AuthorDTO getAuthor(Long id)
    {
        AuthorModel author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author Not Found"));
        return AuthorMapper.INSTANCE.authorToAuthorDTO(author);
    }
}