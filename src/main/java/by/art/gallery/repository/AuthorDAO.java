package by.art.gallery.repository;

import by.art.gallery.repository.entity.Author;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.entity.AuthorsSelect;

import java.util.List;

public interface AuthorDAO {
    Author getById(Long id) throws DAOException;
    void save(Author author) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Author author) throws DAOException;
    List<Author> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    Long count() throws DAOException;
    List<Author> getAuthorForSelect() throws DAOException;
}
