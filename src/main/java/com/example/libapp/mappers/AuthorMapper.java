package com.example.libapp.mappers;

import com.example.libapp.DTOs.AuthorDTO;
import com.example.libapp.models.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper
public interface AuthorMapper
{
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(AuthorModel author);
    AuthorModel authorDTOToAuthorModel(AuthorDTO authorDTO);
    List<AuthorDTO> authorModelToAuthorDTOList(List<AuthorModel> authors);
}
