package com.example.libapp.mappers;
import com.example.libapp.DTOs.BookDTO;
import com.example.libapp.DTOs.BookUpdateDTO;
import com.example.libapp.models.BookModel;
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
