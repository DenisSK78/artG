package by.art.gallery.service;

import by.art.gallery.service.entity.AuthorView;
import by.art.gallery.service.entity.AuthorsSelect;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface AuthorService {
    Long count() throws ServiceException;
    List<AuthorView> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    AuthorView getById(Long id) throws ServiceException;
    List<AuthorsSelect> getAuthorsForSelect() throws ServiceException;
}
