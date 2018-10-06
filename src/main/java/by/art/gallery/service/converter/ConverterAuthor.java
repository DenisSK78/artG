package by.art.gallery.service.converter;

import by.art.gallery.repository.entity.Author;
import by.art.gallery.service.entity.AuthorView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConverterAuthor {

    public static AuthorView toAuthorView(Author author){
        return AuthorView.builder()
                .id(author.getId())
                .alias(author.getAlias())
                .userView(ConverterUser.toUserView(author.getUser()))
                .contract(author.getContract())
                .biography(author.getBiography())
                .dateContract(author.getDateContract())
                .build();
    }

    public static List<AuthorView> toAuthorsView(List<Author> authors){
        return authors.stream()
                .map(ConverterAuthor::toAuthorView)
                .collect(toList());
    }
}
