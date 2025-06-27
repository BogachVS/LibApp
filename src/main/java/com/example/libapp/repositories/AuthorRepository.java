package com.example.libapp.repositories;

import com.example.libapp.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
}
