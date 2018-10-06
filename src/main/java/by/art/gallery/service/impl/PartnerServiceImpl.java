package by.art.gallery.service.impl;

import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.PartnerDAO;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.PartnerService;
import by.art.gallery.service.converter.ConverterPartner;
import by.art.gallery.service.entity.PartnerView;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PartnerServiceImpl implements PartnerService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private PartnerDAO partnerDAO = daoFactory.getPartnerDAO();

    @Override
    public List<PartnerView> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return ConverterPartner.toPartnersView(partnerDAO.getForPage(amount, pageNumber));
        } catch (DAOException e) {
            logger.error("Partner service get partners for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public PartnerView getById(Long id) throws ServiceException {
        try {
            return ConverterPartner.toPartnerView(partnerDAO.getById(id));
        } catch (DAOException e) {
            logger.error("Partner service get partners by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return  partnerDAO.count();
        } catch (DAOException e) {
            logger.error("Partner service count partner", e);
            throw new ServiceException(e);
        }
    }
}
