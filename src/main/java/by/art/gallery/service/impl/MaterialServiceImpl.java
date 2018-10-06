package by.art.gallery.service.impl;

import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.MaterialDAO;
import by.art.gallery.repository.entity.Material;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.MaterialService;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MaterialServiceImpl implements MaterialService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private MaterialDAO materialDAO = daoFactory.getMaterialDAO();

    @Override
    public List<Material> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return materialDAO.getForPage(amount, pageNumber);
        } catch (DAOException e) {
            logger.error("Material service get materials for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Material> getAll() throws ServiceException {
        try {
            return materialDAO.findAll();
        } catch (DAOException e) {
            logger.error("Material service get all materials for select", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Material getById(Long id) throws ServiceException {
        try {
            return materialDAO.getById(id);
        } catch (DAOException e) {
            logger.error("Material service get by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Material material) throws ServiceException {
        try {
            materialDAO.save(material);
        } catch (DAOException e) {
            logger.error("Material service save material", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Material material) throws ServiceException {
        try {
            materialDAO.update(material);
        } catch (DAOException e) {
            logger.error("Material service update material", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            materialDAO.delete(id);
        } catch (DAOException e) {
            logger.error("Material service delete material", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return materialDAO.count();
        } catch (DAOException e) {
            logger.error("Material service count materials", e);
            throw new ServiceException(e);
        }
    }
}
