package by.art.gallery.service.impl;

import by.art.gallery.repository.ArtObjectDAO;
import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.ArtObjectService;
import by.art.gallery.service.converter.ConverterArtObject;
import by.art.gallery.service.entity.ArtObjectView;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtObjectServiceImpl implements ArtObjectService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ArtObjectDAO artObjectDAO = daoFactory.getArtObjectDAO();

    @Override
    public List<ArtObjectView> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return ConverterArtObject.toArtObjectsView(artObjectDAO.getForPage(amount, pageNumber));
        } catch (DAOException e) {
            logger.error("Art-object service get art-objects for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return artObjectDAO.count();
        } catch (DAOException e) {
            logger.error("Art-object service count art-object", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ArtObjectView getById(Long id) throws ServiceException {
        try {
            return ConverterArtObject.toArtObjectView(artObjectDAO.getById(id));
        } catch (DAOException e) {
            logger.error("Art-object service get art-object by ID", e);
            throw new ServiceException(e);
        }
    }
}
