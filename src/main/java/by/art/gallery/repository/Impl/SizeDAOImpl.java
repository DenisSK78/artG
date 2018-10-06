package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.SizeDAO;
import by.art.gallery.repository.entity.Size;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeDAOImpl extends AbstractImpl<Size> implements SizeDAO {

    private static final String WIDTH = "width";
    private static final String LENGTH = "length";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String ART_OBJECT_ID = "art_object_id";
    private static final String GET_BY_ID = "SELECT * FROM gallery.size WHERE art_object_id=?";
    private static final String DELETE = "DELETE FROM gallery.size WHERE art_object_id=?";
    private static final String UPDATE = "UPDATE gallery.size SET width=?, length=?, height=?, weight=? WHERE art_object_id=?";
    private static final String SAVE = "INSERT INTO gallery.size (width, length, height, weight, art_object_id) VALUES (?, ?, ?, ?, ?)";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public Size getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Size size = createResult(resultSet);
            resultSet.close();
            return size;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Size DAO get size by ID", e);
        }
    }

    @Override
    public void save(Size size) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, size);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Size DAO impl save size", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Size DAO impl delete size", e);
        }
    }

    @Override
    public void update(Size size) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, size);
            statement.setLong(5, size.getArtObjectId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Size DAO impl update size", e);
        }
    }

    void setStatement(PreparedStatement statement, Size size) throws SQLException {
            statement.setInt(1, size.getWidth());
            statement.setInt(2, size.getLength());
            statement.setInt(3, size.getHeight());
            statement.setInt(4, size.getWeight());
            statement.setLong(5, size.getArtObjectId());
    }

    Size getGalObject(ResultSet resultSet) throws SQLException {
        return Size.builder()
                .width(resultSet.getInt(WIDTH))
                .length(resultSet.getInt(LENGTH))
                .height(resultSet.getInt(HEIGHT))
                .weight(resultSet.getInt(WEIGHT))
                .artObjectId(resultSet.getLong(ART_OBJECT_ID))
                .build();
    }
}