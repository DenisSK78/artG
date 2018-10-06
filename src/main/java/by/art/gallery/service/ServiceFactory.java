package by.art.gallery.service;

import by.art.gallery.service.impl.AddressServiceImpl;
import by.art.gallery.service.impl.ArtObjectServiceImpl;
import by.art.gallery.service.impl.AuthorServiceImpl;
import by.art.gallery.service.impl.FormArtServiceImpl;
import by.art.gallery.service.impl.MaterialServiceImpl;
import by.art.gallery.service.impl.PartnerServiceImpl;
import by.art.gallery.service.impl.UserServiceImpl;
import by.art.gallery.service.impl.SubcontractorServiceImpl;
import lombok.Getter;

@Getter
public class ServiceFactory {
    private static ServiceFactory instance;

    private UserService userService;
    private AuthorService authorService;
    private FormArtService formArtService;
    private PartnerService partnerService;
    private AddressService addressService;
    private MaterialService materialService;
    private ArtObjectService artObjectService;
    private SubcontractorService subcontractorService;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {
        userService = new UserServiceImpl();
        authorService = new AuthorServiceImpl();
        formArtService = new FormArtServiceImpl();
        partnerService = new PartnerServiceImpl();
        addressService = new AddressServiceImpl();
        materialService = new MaterialServiceImpl();
        artObjectService = new ArtObjectServiceImpl();
        subcontractorService = new SubcontractorServiceImpl();
    }
}
