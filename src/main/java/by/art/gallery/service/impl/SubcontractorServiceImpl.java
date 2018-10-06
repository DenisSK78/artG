package by.art.gallery.service.impl;

import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.SubcontractorDAO;
import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.SubcontractorService;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubcontractorServiceImpl implements SubcontractorService {

    private static final Logger logger = LoggerFactory.getLogger(SubcontractorServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private SubcontractorDAO subcontractorDAO = daoFactory.getSubcontractorDAO();

    @Override
    public List<Subcontractor> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return subcontractorDAO.getForPage(amount, pageNumber);
        } catch (DAOException e) {
            logger.error("Subcontractor service get for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subcontractor> getAll() throws ServiceException {
        try {
            return subcontractorDAO.findAll();
        } catch (DAOException e) {
            logger.error("Subcontractor service get all", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Subcontractor getById(Long id) throws ServiceException {
        try {
            return subcontractorDAO.getById(id);
        } catch (DAOException e) {
            logger.error("Subcontractor service get by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return  subcontractorDAO.count();
        } catch (DAOException e) {
            logger.error("Subcontractor service count", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            subcontractorDAO.delete(id);
        } catch (DAOException e) {
            logger.error("Subcontractor service delete", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Subcontractor sub) throws ServiceException {
        try {
            subcontractorDAO.save(sub);
        } catch (DAOException e) {
            logger.error("Subcontractor service save", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Subcontractor sub) throws ServiceException {
        try {
            subcontractorDAO.update(sub);
        } catch (DAOException e) {
            logger.error("Subcontractor service update", e);
            throw new ServiceException(e);
        }
    }
}
