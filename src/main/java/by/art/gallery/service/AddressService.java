package by.art.gallery.service;

import by.art.gallery.repository.entity.Address;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface AddressService {
    List<Address> getAddressesByUserId(Long id) throws ServiceException;
}
