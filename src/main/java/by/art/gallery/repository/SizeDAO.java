package by.art.gallery.repository;

import by.art.gallery.repository.entity.Size;
import by.art.gallery.repository.exception.DAOException;

public interface SizeDAO {
    Size getById(Long id) throws DAOException;
    void save(Size size) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Size size) throws DAOException;
}
