package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.FormArtDAO;
import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FormArtDAOImpl extends AbstractImpl<FormArt> implements FormArtDAO {

    private static final String ID = "id";
    private static final String RU_NAME_FORM_ART = "ru_name_form_art";
    private static final String EN_NAME_FORM_ART = "en_name_form_art";
    private static final String GET_ALL = "SELECT * FROM gallery.form_art";
    private static final String DELETE = "DELETE FROM gallery.form_art WHERE id=?";
    private static final String GET_FORM_ART_BY_ID = "SELECT * FROM gallery.form_art WHERE id=?";
    private static final String GET_FORM_ART_FOR_PAGE = "SELECT * FROM gallery.form_art LIMIT ? OFFSET ?";
    private static final String UPDATE = "UPDATE gallery.form_art SET ru_name_form_art=?, en_name_form_art=?  WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.form_art (ru_name_form_art, en_name_form_art) VALUES (?, ?)";
    private static final String COUNT = "SELECT COUNT(*) FROM gallery.form_art";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public FormArt getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_FORM_ART_BY_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            FormArt formArt = createResult(resultSet);
            resultSet.close();
            return formArt;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO get form art by id ", e);
        }
    }

    @Override
    public void save(FormArt formArt) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, formArt);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO impl save form art", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO impl delete form art", e);
        }
    }

    @Override
    public void update(FormArt formArt) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, formArt);
            statement.setLong(3, formArt.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO impl update form art", e);
        }
    }

    @Override
    public List<FormArt> findAll() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<FormArt> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO impl get all forms art", e);
        }
    }

    @Override
    public List<FormArt> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_FORM_ART_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<FormArt> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Form art DAO impl get form art for page", e);
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
            throw new DAOException("Form art DAO count forms art", e);
        }
    }

    FormArt getGalObject(ResultSet resultSet) throws SQLException {
        return FormArt.builder()
                .id(resultSet.getLong(ID))
                .ruNameFormArt(resultSet.getString(RU_NAME_FORM_ART))
                .enNameFormArt(resultSet.getString(EN_NAME_FORM_ART))
                .build();
    }

    void setStatement(PreparedStatement statement, FormArt formArt) throws SQLException {
        statement.setString(1, formArt.getRuNameFormArt());
        statement.setString(2, formArt.getEnNameFormArt());
    }
}
