package by.art.gallery.repository;

import by.art.gallery.repository.entity.ArtObject;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface ArtObjectDAO {
    ArtObject getById(Long id) throws DAOException;
    void save(ArtObject artObject) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(ArtObject artObject) throws DAOException;
    List<ArtObject> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    List<ArtObject> getByNameLike(String str) throws DAOException;
    Long count() throws DAOException;
}
