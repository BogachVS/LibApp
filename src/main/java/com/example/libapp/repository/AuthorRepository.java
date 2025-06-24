package com.example.libapp.repository;

import com.example.libapp.repository.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<AuthorModel, Long>
{

}
