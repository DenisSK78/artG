package by.art.gallery.service.impl;

import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.FormArtDAO;
import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.FormArtService;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FormArtServiceImpl implements FormArtService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FormArtDAO formArtDAO = daoFactory.getFormArtDAO();

    @Override
    public List<FormArt> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return formArtDAO.getForPage(amount, pageNumber);
        } catch (DAOException e) {
            logger.error("Form art service get forms art for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public FormArt getById(Long id) throws ServiceException {
        try {
            return formArtDAO.getById(id);
        } catch (DAOException e) {
            logger.error("Form art service get by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(FormArt formArt) throws ServiceException {
        try {
            formArtDAO.update(formArt);
        } catch (DAOException e) {
            logger.error("Form art service update form art", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(FormArt formArt) throws ServiceException {
        try {
            formArtDAO.save(formArt);
        } catch (DAOException e) {
            logger.error("Form art service save form art", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            formArtDAO.delete(id);
        } catch (DAOException e) {
            logger.error("Form art service delete form art by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FormArt> getAll() throws ServiceException {
        try {
            return formArtDAO.findAll();
        } catch (DAOException e) {
            logger.error("Form art service get forms for select", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return formArtDAO.count();
        } catch (DAOException e) {
            logger.error("Form art service count forms", e);
            throw new ServiceException(e);
        }
    }
}
