package by.art.gallery.service.impl;

import by.art.gallery.repository.AddressDAO;
import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.entity.Address;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.AddressService;
import by.art.gallery.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private AddressDAO addressDAO = daoFactory.getAddressDAO();

    @Override
    public List<Address> getAddressesByUserId(Long id) throws ServiceException {
        try {
            return addressDAO.getByUserId(id);
        } catch (DAOException e) {
            logger.error("Address service get addresses by user ID", e);
            throw new ServiceException(e);
        }
    }
}
