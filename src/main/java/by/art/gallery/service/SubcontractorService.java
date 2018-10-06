package by.art.gallery.service;

import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface SubcontractorService {
    Long count() throws ServiceException;
    void delete(Long id) throws ServiceException;
    void save(Subcontractor sub) throws ServiceException;
    void update(Subcontractor sub) throws ServiceException;
    Subcontractor getById(Long id) throws ServiceException;
    List<Subcontractor> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    List<Subcontractor> getAll() throws ServiceException;
}
