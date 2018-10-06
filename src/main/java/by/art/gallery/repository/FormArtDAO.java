package by.art.gallery.repository;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface FormArtDAO {
    FormArt getById(Long id) throws DAOException;
    void save(FormArt formArt) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(FormArt formArt) throws DAOException;
    List<FormArt> findAll() throws DAOException;
    List<FormArt> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    Long count() throws DAOException;
}
