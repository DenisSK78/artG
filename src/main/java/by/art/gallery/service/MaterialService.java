package by.art.gallery.service;

import by.art.gallery.repository.entity.Material;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface MaterialService {
    Long count() throws ServiceException;
    void delete(Long id) throws ServiceException;
    Material getById(Long id) throws ServiceException;
    void save(Material material) throws ServiceException;
    void update(Material material) throws ServiceException;
    List<Material> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    List<Material> getAll() throws ServiceException;
}
