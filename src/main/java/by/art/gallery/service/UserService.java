package by.art.gallery.service;

import by.art.gallery.repository.entity.User;
import by.art.gallery.service.entity.UserView;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.service.entity.UserAuthentication;

import java.util.List;

public interface UserService {
    Long count() throws ServiceException;
    String saveUser(User newUser) throws ServiceException;
    UserView getUserById(Long id) throws ServiceException;
    List<UserView> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    UserAuthentication getForAuth(String email, String password) throws ServiceException;
}
