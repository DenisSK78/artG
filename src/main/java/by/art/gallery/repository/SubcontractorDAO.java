package by.art.gallery.repository;

import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface SubcontractorDAO {
    void save(Subcontractor subcontractor) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Subcontractor subcontractor) throws DAOException;
    Subcontractor getById(Long id) throws DAOException;
    List<Subcontractor> findAll() throws DAOException;
    List<Subcontractor> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    Long count() throws DAOException;
}
