package by.art.gallery.repository;

import by.art.gallery.repository.Impl.AddressDAOImpl;
import by.art.gallery.repository.Impl.ArtObjectDAOImpl;
import by.art.gallery.repository.Impl.AuthorDAOImpl;
import by.art.gallery.repository.Impl.FormArtDAOImpl;
import by.art.gallery.repository.Impl.ImageDAOImpl;
import by.art.gallery.repository.Impl.MaterialDAOImpl;
import by.art.gallery.repository.Impl.PartnerDAOImpl;
import by.art.gallery.repository.Impl.UserDAOImpl;
import by.art.gallery.repository.Impl.SubcontractorDAOImpl;
import by.art.gallery.repository.Impl.SizeDAOImpl;
import lombok.Getter;

@Getter
public class DAOFactory {

    private static DAOFactory instance;

    private UserDAO userDAO;
    private SizeDAO sizeDAO;
    private ImageDAO imageDAO;
    private AuthorDAO authorDAO;
    private AddressDAO addressDAO;
    private FormArtDAO formArtDAO;
    private PartnerDAO partnerDAO;
    private MaterialDAO materialDAO;
    private ArtObjectDAO artObjectDAO;
    private SubcontractorDAO subcontractorDAO;

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    private DAOFactory() {
        sizeDAO = new SizeDAOImpl();
        userDAO = new UserDAOImpl();
        imageDAO = new ImageDAOImpl();
        formArtDAO = new FormArtDAOImpl();
        addressDAO = new AddressDAOImpl();
        materialDAO = new MaterialDAOImpl();
        subcontractorDAO = new SubcontractorDAOImpl();
        authorDAO = new AuthorDAOImpl(new UserDAOImpl());
        partnerDAO = new PartnerDAOImpl(new UserDAOImpl(), new SubcontractorDAOImpl());
        artObjectDAO = new ArtObjectDAOImpl(new AuthorDAOImpl(new UserDAOImpl()), new MaterialDAOImpl(), new FormArtDAOImpl());
    }
}
