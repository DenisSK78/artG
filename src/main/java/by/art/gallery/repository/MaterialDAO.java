package by.art.gallery.repository;

import by.art.gallery.repository.entity.Material;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface MaterialDAO {
    Material getById(Long id) throws DAOException;
    void save(Material material) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Material material) throws DAOException;
    List<Material> findAll() throws DAOException;
    List<Material> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    Long count() throws DAOException;
}
