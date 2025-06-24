package com.example.libapp.controllers;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.repository.models.AuthorModel;
import com.example.libapp.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }
    @PostMapping
    public ResponseEntity<String> AddAuthor(@RequestBody AuthorDTO author)
    {
        try
        {
            authorService.AddAuthor(author);
            return ResponseEntity.ok("Success");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<AuthorModel>> GetAuthors(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "name") String sort)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(authorService.GetAuthorsList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorModel> GetAuthorById(@PathVariable Long id)
    {
        try
        {
            AuthorModel author = authorService.GetAuthor(id);
            return ResponseEntity.ok(author);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}