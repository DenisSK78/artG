package by.art.gallery.service;

import by.art.gallery.service.entity.ArtObjectView;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface ArtObjectService {
    List<ArtObjectView> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    Long count() throws ServiceException;
    ArtObjectView getById(Long id) throws ServiceException;
}
