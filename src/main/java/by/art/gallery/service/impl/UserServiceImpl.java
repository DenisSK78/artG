package by.art.gallery.service.impl;

import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.UserDAO;
import by.art.gallery.repository.entity.Role;
import by.art.gallery.repository.entity.Status;
import by.art.gallery.repository.entity.User;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.UserService;
import by.art.gallery.service.entity.UserView;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.service.entity.UserAuthentication;
import by.art.gallery.service.converter.ConverterUser;
import by.art.gallery.service.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String BAD_DATA = "bad_data";
    private static final String EMAIL_EXIST = "email_exist";
    private static final String SUCCESS = "success";

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDAO();


    @Override
    public UserAuthentication getForAuth(String email, String password) throws ServiceException {

        User user;

        if (Validator.validateEmail(email)
                || Validator.validatePassword(password)){
            return null;
        }

        try {
            user = userDAO.getByEmail(email);
        } catch (DAOException e) {
            logger.error("User service get user by email", e);
            throw new ServiceException(e);
        }

        if(user!=null && user.getEmail().equals(email)
                && user.getPassword().equals(password)) {
            return UserAuthentication.builder()
                    .id(user.getId())
                    .role(user.getRole().toString())
                    .status(user.getStatus().toString())
                    .build();
        }
        return null;
    }

    @Override
    public String saveUser(User newUser) throws ServiceException {

        User user;

        if (Validator.validateEmail(newUser.getEmail())
                || Validator.validateTextField(newUser.getFirstName())
                || Validator.validateTextField(newUser.getSurname())
                || Validator.validatePassword(newUser.getPassword())){
            return BAD_DATA;
        }

        try {
            user = userDAO.getByEmail(newUser.getEmail());
            if (user != null){
                return EMAIL_EXIST;
            }
        } catch (DAOException e) {
            logger.error("User service get user by email", e);
            throw new ServiceException(e);
        }

        newUser.setRole(Role.GUEST);
        newUser.setStatus(Status.OK);

        try {
            userDAO.save(newUser);
            return SUCCESS;
        } catch (DAOException e) {
            logger.error("User service save user", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserView> getForPage(Integer amount, Integer pageNumber) throws ServiceException {
        try {
            return ConverterUser.toUsersView(userDAO.getForPage(amount, pageNumber));
        } catch (DAOException e) {
            logger.error("User service get users for page", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long count() throws ServiceException {
        try {
            return userDAO.count();
        } catch (DAOException e) {
            logger.error("User service count users", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserView getUserById(Long id) throws ServiceException{
        try {
            return ConverterUser.toUserView(userDAO.getById(id));
        } catch (DAOException e) {
            logger.error("User service get user by ID", e);
            throw new ServiceException(e);
        }
    }
}