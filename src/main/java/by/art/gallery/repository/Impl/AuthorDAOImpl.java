package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.AuthorDAO;
import by.art.gallery.repository.entity.Author;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl extends AbstractImpl<Author> implements AuthorDAO {

    private static final String DATE_CONTRACT = "date_contract";
    private static final String CONTRACT = "contract";
    private static final String ALIAS = "alias";
    private static final String USER_ID = "user_id";
    private static final String ID = "id";
    private static final String DELETE = "DELETE FROM gallery.author WHERE id=?";
    private static final String GET_AUTHORS_FOR_PAGE = "SELECT * FROM gallery.author a JOIN gallery.users u ON u.id = a.user_id LIMIT ? OFFSET ?";
    private static final String GET_BY_ID = "SELECT * FROM gallery.author a JOIN gallery.users u ON u.id = a.user_id WHERE a.id=?";
    private static final String SAVE = "INSERT INTO gallery.author (user_id, alias, contract, biography, date_contract) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE gallery.author SET user_id=?, alias=?, contract=?, biography=?, date_contract=? WHERE id=?";
    private static final String GET_AUTHORS_FOR_SELECT = "SELECT author.id, author.alias FROM gallery.author";
    private static final String COUNT = "SELECT COUNT(*) FROM gallery.author";

    private ConnectionPool connection = ConnectionPool.getInstance();
    private UserDAOImpl userDAO;

    public AuthorDAOImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Author getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Author author = createResult(resultSet);
            resultSet.close();
            return author;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO get author by ID ", e);
        }
    }

    @Override
    public void save(Author author) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, author);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO impl save author", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO impl delete author", e);
        }
    }

    @Override
    public void update(Author author) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, author);
            statement.setLong(6, author.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO impl update author", e);
        }
    }

    @Override
    public List<Author> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AUTHORS_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<Author> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO impl get authors for page", e);
        }
    }

    @Override
    public Long count() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT)) {
            ResultSet resultSet = statement.executeQuery();
            Long result = resultSet.next() ? resultSet.getLong(1) : null;
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author count count authors", e);
        }
    }

    @Override
    public List<Author> getAuthorForSelect() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AUTHORS_FOR_SELECT)) {
            ResultSet resultSet = statement.executeQuery();
            List<Author> result = new ArrayList<>();
            while (resultSet.next()){
                result.add(Author.builder()
                        .id(resultSet.getLong(ID))
                        .alias(resultSet.getString(ALIAS))
                        .build());
            }
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Author DAO impl get authors for select", e);
        }
    }

    Author getGalObject(ResultSet resultSet) throws SQLException {
        Author author =  Author.builder()
                .id(resultSet.getLong(ID))
                .user(userDAO.getGalObject(resultSet))
                .alias(resultSet.getString(ALIAS))
                .contract(resultSet.getString(CONTRACT))
                .dateContract(resultSet.getDate(DATE_CONTRACT))
                .build();
        author.getUser().setId(resultSet.getLong(USER_ID));
        return author;
    }

    void setStatement(PreparedStatement statement, Author author) throws SQLException {
        statement.setLong(1, author.getUser().getId());
        statement.setString(2, author.getAlias());
        statement.setString(3, author.getContract());
        statement.setString(4, author.getBiography());
        statement.setDate(5, author.getDateContract());
    }
}
