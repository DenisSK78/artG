package by.art.gallery.service;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface FormArtService {
    Long count() throws ServiceException;
    List<FormArt> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    FormArt getById(Long id) throws ServiceException;
    void update(FormArt formArt) throws ServiceException;
    void save(FormArt formArt) throws ServiceException;
    void delete(Long id) throws ServiceException;
    List<FormArt> getAll() throws ServiceException;
}
