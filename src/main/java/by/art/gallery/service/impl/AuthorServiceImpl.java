package by.art.gallery.service.impl;

import by.art.gallery.repository.AuthorDAO;
import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.entity.Author;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.AuthorService;
import by.art.gallery.service.converter.ConverterAuthor;
import by.art.gallery.service.entity.AuthorView;
import by.art.gallery.service.entity.AuthorsSelect;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private AuthorDAO authorDAO = daoFactory.getAuthorDAO();

    @Override
    public List<AuthorView> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return ConverterAuthor.toAuthorsView(authorDAO.getForPage(amount, pageNumber));
        } catch (DAOException e) {
            logger.error("Author service get author for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public AuthorView getById(Long id) throws ServiceException {
        try {
            return ConverterAuthor.toAuthorView(authorDAO.getById(id));
        } catch (DAOException e) {
            logger.error("Author service get author by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<AuthorsSelect> getAuthorsForSelect() throws ServiceException {
        try {
            List<Author> authors = authorDAO.getAuthorForSelect();
            return authors
                    .stream()
                    .map(a -> new AuthorsSelect(a.getId(), a.getAlias()))
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Author service get authors for select", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return authorDAO.count();
        } catch (DAOException e) {
            logger.error("Author service count authors", e);
            throw new ServiceException(e);
        }
    }
}
