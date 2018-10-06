package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.MaterialDAO;
import by.art.gallery.repository.entity.Material;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MaterialDAOImpl extends AbstractImpl<Material> implements MaterialDAO {

    private static final String ID = "id";
    private static final String RU_NAME_MATERIAL = "ru_name_material";
    private static final String EN_NAME_MATERIAL = "en_name_material";
    private static final String GET_BY_ID = "SELECT * FROM gallery.material WHERE id=?";
    private static final String GET_MATERIAL_FOR_PAGE = "SELECT * FROM gallery.material LIMIT ? OFFSET ?";
    private static final String GET_ALL = "SELECT * FROM gallery.material";
    private static final String UPDATE = "UPDATE gallery.material SET ru_name_material=?, en_name_material=?  WHERE id=?";
    private static final String DELETE = "DELETE FROM gallery.material WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.material (ru_name_material, en_name_material) VALUES (?, ?)";
    private static final String COUNT = "SELECT COUNT(*) FROM gallery.material";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public Material getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Material material = createResult(resultSet);
            resultSet.close();
            return material;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl get material by ID", e);
        }
    }

    @Override
    public void save(Material material) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement,material);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl save material", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl delete material", e);
        }
    }

    @Override
    public void update(Material material) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, material);
            statement.setLong(3, material.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl update material", e);
        }
    }

    @Override
    public List<Material> findAll() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Material> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl get all materials", e);
        }
    }

    @Override
    public List<Material> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_MATERIAL_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<Material> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Material DAO impl get materials for page", e);
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
            throw new DAOException("Material DAO count materials", e);
        }
    }

    Material getGalObject(ResultSet resultSet) throws SQLException {
        return Material.builder()
                .id(resultSet.getLong(ID))
                .ruNameMaterial(resultSet.getString(RU_NAME_MATERIAL))
                .enNameMaterial(resultSet.getString(EN_NAME_MATERIAL))
                .build();
    }

    void setStatement(PreparedStatement statement, Material material) throws SQLException {
        statement.setString(1, material.getRuNameMaterial());
        statement.setString(2, material.getEnNameMaterial());
    }
}
