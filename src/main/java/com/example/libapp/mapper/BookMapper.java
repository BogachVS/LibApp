package com.example.libapp.mapper;
import com.example.libapp.DTO.BookDTO;
import com.example.libapp.DTO.BookUpdateDTO;
import com.example.libapp.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper
{
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @Mapping(target = "authorId", source = "author.id")
    BookDTO bookToBookDTO(BookModel book);
    @Mapping(target = "author", ignore = true)
    void updateBookFromBookDTO(BookUpdateDTO bookDTO, @MappingTarget BookModel book);
    List<BookDTO> booksToBookDTOs(List<BookModel> books);
    @Mapping(target = "author", ignore = true)
    BookModel bookDTOToBookModel(BookDTO bookDTO);
}
