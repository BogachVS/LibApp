package com.example.libapp.serviceTest;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.model.AuthorModel;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests
{
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;

    @Test
    void whenAddAuthor_thenSaveCorrectly()
    {
        AuthorDTO authorDTO = new AuthorDTO("Александр Пушкин", 1799);
        AuthorModel authorModel = new AuthorModel(1L,"Александр Пушкин", 1799);
        when(authorRepository.save(any(AuthorModel.class))).thenReturn(authorModel);
        authorService.addAuthor(authorDTO);
        verify(authorRepository, times(1)).save(any(AuthorModel.class));
    }

    @Test
    void getAuthorsList_ReturnsPage()
    {
        List<AuthorModel> authors = List.of(
                new AuthorModel(1L, "Лев Толстой", 1828),
                new AuthorModel(2L, "Фёдор Достоевский", 1821)
        );
        when(authorRepository.findAll()).thenReturn(authors);
        Page<AuthorDTO> result = authorService.getAuthorsList();
        assertEquals(2, result.getContent().size());
        assertEquals("Лев Толстой", result.getContent().getFirst().getName());
    }

    @Test
    void getAuthorById_thenFindSuccessfully()
    {
        AuthorModel author = new AuthorModel(1L, "Лев Толстой", 1828);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        AuthorDTO result = authorService.getAuthor(1L);
        assertEquals("Лев Толстой", result.getName());
        assertEquals(1828, result.getBirthYear());
    }

    @Test
    void getAuthorById_theNotFound()
    {
        assertTrue(authorRepository.findById(1L).isEmpty());
    }
}
