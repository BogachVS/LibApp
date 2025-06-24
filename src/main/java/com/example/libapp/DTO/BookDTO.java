package com.example.libapp.DTO;

import lombok.Data;

@Data
public class BookDTO
{
    public String title;
    public Long author_id;
    public int year;
    public String genre;
}