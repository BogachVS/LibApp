package com.example.libapp.controller;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController
{
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDTO author)
    {
        try
        {
            authorService.addAuthor(author);
            return ResponseEntity.ok("Author successfully added");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> getAuthors()
    {
        try
        {
            return ResponseEntity.ok(authorService.getAuthorsList());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id)
    {
        try
        {
            AuthorDTO author = authorService.getAuthor(id);
            return ResponseEntity.ok(author);
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}