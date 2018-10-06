package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.ArtObjectDAO;
import by.art.gallery.repository.entity.ArtObject;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArtObjectDAOImpl extends AbstractImpl<ArtObject> implements ArtObjectDAO {

    private static final String ID = "id";
    private static final String AUTHOR_ID = "author_id";
    private static final String OBJECT_NAME = "object_name";
    private static final String OBJECT_INFO = "object_info";
    private static final String FORM_ART_ID = "form_art_id";
    private static final String MATERIAL_ID = "material_id";
    private static final String DATE_CREATION = "date_creation";
    private static final String DESCRIPTION_OBJECT = "description_object";
    private static final String GET_BY_ID = "SELECT * FROM gallery.art_object obj JOIN gallery.author a ON a.id = obj.author_id JOIN gallery.material m ON m.id = obj.material_id JOIN gallery.form_art f ON f.id = obj.form_art_id JOIN gallery.users u ON u.id=a.user_id WHERE obj.id = ?";
    private static final String GET_USERS_FOR_PAGE = "SELECT * FROM gallery.art_object obj JOIN gallery.author a ON a.id = obj.author_id JOIN gallery.material m ON m.id = obj.material_id JOIN gallery.form_art f ON f.id = obj.form_art_id JOIN gallery.users u ON u.id=a.user_id ORDER BY obj.id LIMIT ? OFFSET ?";
    private static final String SAVE = "INSERT INTO gallery.art_object (object_name, description_object, date_creation, object_info, author_id, form_art_id, material_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE gallery.art_object SET object_name=?, description_object=?, date_creation=?, object_info=?, author_id=?, form_art_id=?, material_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM gallery.art_object WHERE id=?";
    private static final String GET_BY_NAME_LIKE = "SELECT * FROM gallery.art_object obj JOIN gallery.author a ON a.id = obj.author_id JOIN gallery.material m ON m.id = obj.material_id JOIN gallery.form_art f ON f.id = obj.form_art_id JOIN gallery.users u ON u.id=a.user_id WHERE lower(object_name) LIKE ?";
    private static final String COUNT = "SELECT COUNT(*) FROM gallery.art_object";

//found_by_parameters = SELECT * FROM gallery.art_object WHERE

    private ConnectionPool connection = ConnectionPool.getInstance();
    private final AuthorDAOImpl authorDAO;
    private final FormArtDAOImpl formArtDAO;
    private final MaterialDAOImpl materialDAO;

    public ArtObjectDAOImpl(AuthorDAOImpl authorDAO, MaterialDAOImpl materialDAO, FormArtDAOImpl formArtDAO) {
        this.authorDAO = authorDAO;
        this.formArtDAO = formArtDAO;
        this.materialDAO = materialDAO;
    }

    @Override
    public ArtObject getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArtObject artObject = createResult(resultSet);
            resultSet.close();
            return artObject;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object DAO get art-object by ID", e);
        }
    }

    @Override
    public void save(ArtObject artObject) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, artObject);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object DAO impl save art-object", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object DAO impl delete art-object", e);
        }
    }

    @Override
    public void update(ArtObject artObject) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, artObject);
            statement.setLong(8, artObject.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object DAO impl update art-object", e);
        }
    }

    @Override
    public List<ArtObject> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USERS_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<ArtObject> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object impl get art-objects for page", e);
        }
    }

    @Override
    public List<ArtObject> getByNameLike(String str) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_NAME_LIKE)) {
            statement.setString(1, "%"+str+"%");
            ResultSet resultSet = statement.executeQuery();
            List<ArtObject> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Art-object impl get by name like", e);
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
            throw new DAOException("Art-object count count art-object", e);
        }
    }

    void setStatement(PreparedStatement statement, ArtObject artObject) throws SQLException {
        statement.setString(1, artObject.getObjectName());
        statement.setString(2, artObject.getDescriptionObject());
        statement.setDate(3, artObject.getDateCreation());
        statement.setString(4, artObject.getObjectInfo());
        statement.setLong(5, artObject.getAuthor().getId());
        statement.setLong(6, artObject.getFormArt().getId());
        statement.setLong(7, artObject.getMaterial().getId());
    }

    ArtObject getGalObject(ResultSet resultSet) throws SQLException {
        ArtObject artObject = ArtObject.builder()
                .id(resultSet.getLong(ID))
                .objectName(resultSet.getString(OBJECT_NAME))
                .descriptionObject(resultSet.getString(DESCRIPTION_OBJECT))
                .dateCreation(resultSet.getDate(DATE_CREATION))
                .objectInfo(resultSet.getString(OBJECT_INFO))
                .author(authorDAO.getGalObject(resultSet))
                .material(materialDAO.getGalObject(resultSet))
                .formArt(formArtDAO.getGalObject(resultSet))
                .build();
        artObject.getFormArt().setId(resultSet.getLong(FORM_ART_ID));
        artObject.getMaterial().setId(resultSet.getLong(MATERIAL_ID));
        artObject.getAuthor().setId(resultSet.getLong(AUTHOR_ID));
        return artObject;
    }
}
