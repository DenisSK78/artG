package by.art.gallery.repository;

import by.art.gallery.repository.entity.Address;
import by.art.gallery.repository.entity.Size;
import by.art.gallery.repository.exception.DAOException;

import java.time.LocalDateTime;
import java.util.List;

public interface AddressDAO {
    Address getById(Long id) throws DAOException;
    void save(Address address) throws DAOException;
    void delete(Long id) throws DAOException;
    void update(Address address) throws DAOException;
    List<Address> getByUserId(Long id) throws DAOException;
}
