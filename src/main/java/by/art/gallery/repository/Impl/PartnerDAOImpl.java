package by.art.gallery.repository.Impl;

import by.art.gallery.repository.config.ConnectionPool;
import by.art.gallery.repository.PartnerDAO;
import by.art.gallery.repository.entity.Partner;
import by.art.gallery.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PartnerDAOImpl extends AbstractImpl<Partner> implements PartnerDAO {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String PARTNER_INFO = "partner_info";
    private static final String WORK_TYPE_ID = "work_type_id";
    private static final String GET_PARTNER_FOR_PAGE = "SELECT * FROM gallery.partner p JOIN gallery.users u ON u.id = p.user_id JOIN gallery.work_type pt ON pt.id = p.work_type_id LIMIT ? OFFSET ?";
    private static final String COUNT_PARTNER = "SELECT COUNT(*) FROM gallery.partner";
    private static final String GET_BY_ID = "SELECT * FROM gallery.partner p JOIN gallery.users u ON u.id = p.user_id JOIN gallery.work_type w ON w.id = p.work_type_id WHERE p.id=?";
    private static final String GET_PARTNER_BY_WORK_ID = "SELECT * FROM gallery.partner p JOIN gallery.users u ON u.id = p.user_id JOIN gallery.work_type w ON w.id = p.work_type_id WHERE p.work_type_id=?";
    private static final String SAVE = "INSERT INTO gallery.partner (partner_info, work_type_id, user_id) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM gallery.partner WHERE id=?";
    private static final String UPDATE = "UPDATE gallery.partner SET partner_info=?, work_type_id=?, user_id=? WHERE id=?";

    private ConnectionPool connection = ConnectionPool.getInstance();
    private UserDAOImpl userDAOImpl;
    private SubcontractorDAOImpl subcontractorDAO;

    public PartnerDAOImpl(UserDAOImpl userDAOImpl, SubcontractorDAOImpl partnerTypeDAOImpl) {
        this.userDAOImpl = userDAOImpl;
        this.subcontractorDAO = partnerTypeDAOImpl;
    }

    @Override
    public List<Partner> getForPage(Integer amount, Integer pageNumber) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PARTNER_FOR_PAGE)) {
            statement.setInt(1, amount);
            statement.setInt(2, (pageNumber - 1) * amount);
            ResultSet resultSet = statement.executeQuery();
            List<Partner> partners = createResultList(resultSet);
            resultSet.close();
            return partners;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO impl get partner for page", e);
        }
    }

    @Override
    public List<Partner> getWorkTypeId(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PARTNER_BY_WORK_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Partner> partners = createResultList(resultSet);
            resultSet.close();
            return partners;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO impl get partners by work ID", e);
        }
    }

    @Override
    public void save(Partner partner) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            setStatement(statement, partner);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO impl save partner", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO impl delete partner", e);
        }
    }

    @Override
    public void update(Partner partner) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setStatement(statement, partner);
            statement.setLong(4, partner.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO impl update partner", e);
        }
    }

    @Override
    public Long count() throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_PARTNER)) {
            ResultSet resultSet = statement.executeQuery();
            Long result = resultSet.next() ? resultSet.getLong(1) : null;
            resultSet.close();
            return result;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO count partners", e);
        }
    }

    @Override
    public Partner getById(Long id) throws DAOException {
        try (Connection connection = this.connection.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Partner partner = createResult(resultSet);
            resultSet.close();
            return partner;
        } catch (SQLException | InterruptedException e) {
            throw new DAOException("Partner DAO get partner by ID", e);
        }
    }

    Partner getGalObject(ResultSet resultSet) throws SQLException {
        Partner partner = Partner.builder()
                .id(resultSet.getLong(ID))
                .partnerInfo(resultSet.getString(PARTNER_INFO))
                .user(userDAOImpl.getGalObject(resultSet))
                .subcontractor(subcontractorDAO.getGalObject(resultSet))
                .build();
        partner.getUser().setId(resultSet.getLong(USER_ID));
        partner.getSubcontractor().setId(resultSet.getLong(WORK_TYPE_ID));
        return partner;
    }

    void setStatement(PreparedStatement statement, Partner partner) throws SQLException {
        statement.setString(1, partner.getPartnerInfo());
        statement.setLong(2, partner.getSubcontractor().getId());
        statement.setLong(3, partner.getUser().getId());
    }
}
