package by.art.gallery.repository;

import by.art.gallery.repository.entity.Image;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface ImageDAO {
    Image getById(Long id) throws DAOException;
    void save(Image image) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Image image) throws DAOException;
    List<Image> getForPage(Integer amount, Integer pageNumber) throws DAOException;
}
