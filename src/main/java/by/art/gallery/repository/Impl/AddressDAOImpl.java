package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.AddressDAO;
import by.art.gallery.repository.entity.Address;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDAOImpl extends AbstractImpl<Address> implements AddressDAO {

    private static final String ID = "id";
    private static final String CITY = "city";
    private static final String FLAT = "flat";
    private static final String PHONE = "phone";
    private static final String HOUSE = "house";
    private static final String STREET = "street";
    private static final String USER_ID = "user_id";
    private static final String COUNTRY = "country";
    private static final String POSTCODE = "postcode";
    private static final String BUILDING = "building";
    private static final String GET_BY_ID = "SELECT * FROM gallery.address WHERE id=?";
    private static final String DELETE = "DELETE FROM gallery.address WHERE id=?";
    private static final String UPDATE = "UPDATE gallery.address SET country=?, city=?, street=?, house=?, building=?, flat=?, phone=?, postcode=?, user_id=? WHERE id=?";
    private static final String SAVE = "INSERT INTO gallery.address (country, city, street, house, building, flat, phone, postcode, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ADDRESS_BY_USER_ID = "SELECT * FROM gallery.address WHERE user_id=?";

    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public Address getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Address address = createResult(resultSet);
            resultSet.close();
            return address;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Address DAO get address by ID ", e);
        }
    }

    @Override
    public void save(Address address) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, address);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("User DAO impl save user", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Address DAO impl delete address", e);
        }
    }

    @Override
    public void update(Address address) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, address);
            statement.setLong(10, address.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Address DAO impl update address", e);
        }
    }

    @Override
    public List<Address> getByUserId(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ADDRESS_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Address> result = createResultList(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Address DAO impl get address for page", e);
        }
    }

    void setStatement(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(1, address.getCountry());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getStreet());
        statement.setString(4, address.getHouse());
        statement.setString(5, address.getBuilding());
        statement.setString(6, address.getFlat());
        statement.setString(7, address.getPhone());
        statement.setString(8, address.getPostCode());
        statement.setLong(9, address.getUserId());
    }

    @Override
    Address getGalObject(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .id(resultSet.getLong(ID))
                .city(resultSet.getString(CITY))
                .flat(resultSet.getString(FLAT))
                .house(resultSet.getString(HOUSE))
                .phone(resultSet.getString(PHONE))
                .street(resultSet.getString(STREET))
                .country(resultSet.getString(COUNTRY))
                .building(resultSet.getString(BUILDING))
                .postCode(resultSet.getString(POSTCODE))
                .userId(resultSet.getLong(USER_ID))
                .build();
    }
}
