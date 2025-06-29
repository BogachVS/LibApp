package com.example.libapp.mapper;

import com.example.libapp.DTO.AuthorDTO;
import com.example.libapp.model.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper
{
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(AuthorModel author);
    AuthorModel authorDTOToAuthorModel(AuthorDTO authorDTO);
    List<AuthorDTO> authorModelToAuthorDTOList(List<AuthorModel> authors);
}
