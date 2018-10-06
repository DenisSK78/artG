package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.SubcontractorDAO;
import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubcontractorDAOImpl extends AbstractImpl<Subcontractor> implements SubcontractorDAO {

    private static final String ID = "id";
    private static final String NAME_WORK = "name_work";
    private static final String GET_ALL = "SELECT * FROM gallery.work_type";
    private static final String DELETE = "DELETE FROM gallery.work_type WHERE id=?";
    private static final String GET_BY_ID = "SELECT * FROM gallery.work_type WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.work_type (name_work) VALUES (?)";
    private static final String UPDATE = "UPDATE gallery.work_type SET name_work=? WHERE id=?";
    private static final String GET_NAMES_FOR_PAGE = "SELECT * FROM gallery.work_type LIMIT ? OFFSET ?";
    private static final String COUNT = "SELECT COUNT(*) FROM gallery.work_type";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public Subcontractor getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Subcontractor subcontractor = createResult(resultSet);
            resultSet.close();
            return subcontractor;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl get by ID", e);
        }
    }

    @Override
    public void save(Subcontractor subcontractor) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, subcontractor);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl save", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl delete", e);
        }
    }

    @Override
    public void update(Subcontractor subcontractor) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, subcontractor);
            statement.setLong(2, subcontractor.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl update", e);
        }
    }

    @Override
    public List<Subcontractor> findAll() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Subcontractor> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl get all", e);
        }
    }

    @Override
    public List<Subcontractor> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NAMES_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<Subcontractor> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Subcontractor DAO impl get for page", e);
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
            throw new DAOException("Subcontractor DAO impl count", e);
        }
    }

    Subcontractor getGalObject(ResultSet resultSet) throws SQLException {
        return Subcontractor.builder()
                .id(resultSet.getLong(ID))
                .occupation(resultSet.getString(NAME_WORK))
                .build();
    }

    void setStatement(PreparedStatement statement, Subcontractor subcontractor) throws SQLException {
        statement.setString(1, subcontractor.getOccupation());
    }
}
