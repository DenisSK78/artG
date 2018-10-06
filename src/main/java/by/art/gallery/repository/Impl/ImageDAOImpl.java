package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.ImageDAO;
import by.art.gallery.repository.entity.Image;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageDAOImpl extends AbstractImpl<Image> implements ImageDAO {

    private static final String ID = "id";
    private static final String IMAGE_OBJECT_PATH = "image_path";
    private static final String ART_OBJECT_ID = "art_object_id";
    private static final String GET_BY_ID = "SELECT * FROM gallery.image WHERE id=?";
    private static final String GET_IMAGE_FOR_PAGE = "SELECT * FROM gallery.image LIMIT ? OFFSET ?";
    private static final String UPDATE = "UPDATE gallery.image SET image_path=?, art_object_id=? WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.image (image_path, art_object_id) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM gallery.image WHERE id=?";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public Image getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Image image = createResult(resultSet);
            resultSet.close();
            return image;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Image DAO get image by ID", e);
        }
    }

    @Override
    public void save(Image image) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, image);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Image DAO impl save image", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Image DAO impl delete image", e);
        }
    }

    @Override
    public void update(Image image) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, image);
            statement.setLong(3, image.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Image DAO impl update image", e);
        }
    }

    @Override
    public List<Image> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_IMAGE_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<Image> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Image DAO impl get images for page", e);
        }
    }

    void setStatement(PreparedStatement statement, Image image) throws SQLException {
        statement.setString(1, image.getPath());
        statement.setLong(2, image.getArtObjectId());
    }

    Image getGalObject(ResultSet resultSet) throws SQLException {
        return Image.builder()
                .id(resultSet.getLong(ID))
                .path(resultSet.getString(IMAGE_OBJECT_PATH))
                .artObjectId(resultSet.getLong(ART_OBJECT_ID))
                .build();
    }
}
