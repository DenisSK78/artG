package by.art.gallery.repository;

import by.art.gallery.repository.entity.User;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public interface UserDAO {
    Long count() throws DAOException;
    void save(User user) throws DAOException;
    void delete(Long id) throws DAOException;
    User getById(Long id) throws DAOException;
    void update(User user) throws DAOException;
    User getByEmail(String email) throws DAOException;
    List<User> getForPage(Integer amount, Integer pageNumber) throws DAOException;
}
