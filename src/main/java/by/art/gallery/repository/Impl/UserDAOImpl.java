package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.UserDAO;
import by.art.gallery.repository.entity.Gender;
import by.art.gallery.repository.entity.Role;
import by.art.gallery.repository.entity.Status;
import by.art.gallery.repository.entity.User;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl extends AbstractImpl<User> implements UserDAO {

    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String STATUS = "status";
    private static final String GENDER = "gender";
    private static final String SURNAME = "surname";
    private static final String PASSWORD = "password";
    private static final String BIRTHDAY = "birthday";
    private static final String FIRST_NAME = "first_name";
    private static final String DELETE = "DELETE FROM gallery.users WHERE id=?";
    private static final String COUNT_USER = "SELECT COUNT(*) FROM gallery.users";
    private static final String GET_BY_ID = "SELECT * FROM gallery.users WHERE id=?";
    private static final String GET_USERS_FOR_PAGE = "SELECT * FROM gallery.users LIMIT ? OFFSET ?";
    private static final String GET_BY_EMAIL_LIKE = "SELECT * FROM gallery.users WHERE email LIKE ?";
    private static final String UPDATE = "UPDATE gallery.users SET first_name=?, surname=?, email=?, password=?, role=?, status=?, birthday=?, gender=? WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.users (first_name, surname, email, password, role, status, birthday, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public User getByEmail(String email) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_LIKE)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = createResult(resultSet);
            resultSet.close();
            return user;
        } catch (InterruptedException | SQLException e) {
            throw new DAOException("User DAO impl get user by email", e);
        }
    }

    @Override
    public List<User> getForPage(Integer amount, Integer pageNumber) throws DAOException{
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USERS_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<User> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO impl get users for page", e);
        }
    }

    @Override
    public Long count() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_USER)) {
            ResultSet resultSet = statement.executeQuery();
            Long result = resultSet.next() ? resultSet.getLong(1) : null;
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO count users", e);
        }
    }

    @Override
    public User getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = createResult(resultSet);
            resultSet.close();
            return user;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO get user by ID", e);
        }
    }

    @Override
    public void save(User user) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, user);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO impl save user", e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, user);
            statement.setLong(9, user.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO impl update user", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO impl delete user", e);
        }
    }

    @Override
    User getGalObject(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(ID))
                .email(resultSet.getString(EMAIL))
                .surname(resultSet.getString(SURNAME))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase(Locale.ENGLISH)))
                .gender(Gender.valueOf(resultSet.getString(GENDER).toUpperCase(Locale.ENGLISH)))
                .status(Status.valueOf(resultSet.getString(STATUS).toUpperCase(Locale.ENGLISH)))
                .dateBirthday(resultSet.getDate(BIRTHDAY))
                .build();
    }

    void setStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getRole().toString());
        statement.setString(6, user.getStatus().toString());
        statement.setString(8, user.getGender().toString());
        statement.setDate(7, user.getDateBirthday());
    }
}