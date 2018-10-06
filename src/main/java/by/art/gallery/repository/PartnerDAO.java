package by.art.gallery.repository;

import by.art.gallery.repository.entity.Partner;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface PartnerDAO {
    Long count() throws DAOException;
    Partner getById(Long id) throws DAOException;
    List<Partner> getForPage(Integer amount, Integer pageNumber) throws DAOException;
    void save(Partner partner) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Partner partner) throws DAOException;
    List<Partner> getWorkTypeId(Long id) throws DAOException;
}
