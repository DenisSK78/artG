import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.entity.Address;
import by.art.gallery.repository.entity.ArtObject;
import by.art.gallery.repository.entity.Author;
import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.entity.Image;
import by.art.gallery.repository.entity.Material;
import by.art.gallery.repository.entity.Partner;
import by.art.gallery.repository.entity.Role;
import by.art.gallery.repository.entity.Size;
import by.art.gallery.repository.entity.Status;
import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.repository.entity.User;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.converter.ConverterEnum;
import by.art.gallery.view.command.impl.SignOut;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SQLRequestTest {

    private final static Logger logger = LoggerFactory.getLogger(SignOut.class);

    private DAOFactory daoFactory;

    @Before
    public void createObjects(){
        daoFactory = DAOFactory.getInstance();
    }

    @Test
    public void requestUser(){
        User user = User.builder()
                .firstName("Test")
                .surname("Test")
                .email("test@test.test")
                .gender(ConverterEnum.getGender("male"))
                .password("test-test")
                .role(Role.USER)
                .status(Status.OK)
                .dateBirthday(Date.valueOf(LocalDate.now()))
                .build();

        try {
            daoFactory.getUserDAO().save(user);
            user = null;
            user = daoFactory.getUserDAO().getByEmail("test@test.test");
            Long id = user.getId();
            user = null;
            user = daoFactory.getUserDAO().getById(id);
            user.setStatus(Status.BAN);
            daoFactory.getUserDAO().update(user);
            daoFactory.getUserDAO().delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void requestSize() {
        Size size = Size.builder()
                .weight(100)
                .height(200)
                .artObjectId(4L)
                .build();

        try {
            daoFactory.getSizeDAO().save(size);
            size = null;
            size = daoFactory.getSizeDAO().getById(4L);
            size.setLength(10);
            daoFactory.getSizeDAO().update(size);
            daoFactory.getSizeDAO().delete(4L);
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestKindOfWork() {
        Subcontractor subcontractor = Subcontractor.builder()
                .occupation("Work")
                .build();

        try {
            daoFactory.getSubcontractorDAO().save(subcontractor);
            subcontractor = null;
            List<Subcontractor> list = daoFactory.getSubcontractorDAO().findAll();
            subcontractor = null;
            subcontractor = daoFactory.getSubcontractorDAO().getById(list.get(list.size()-1).getId());
            subcontractor.setOccupation("Work-too");
            daoFactory.getSubcontractorDAO().update(subcontractor);
//            daoFactory.getSubcontractorDAO().delete(subcontractor.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestPartner() {
        try {
        Partner partner = Partner.builder()
                .partnerInfo("bla-bla-bla")
                .subcontractor(daoFactory.getSubcontractorDAO().getById(1L))
                .user(daoFactory.getUserDAO().getById(3L))
                .build();

            daoFactory.getPartnerDAO().save(partner);
            partner = null;
            List<Partner> list = daoFactory.getPartnerDAO().getWorkTypeId(1L);
            partner = null;
            partner = daoFactory.getPartnerDAO().getById(list.get(list.size()-1).getId());
            partner.setPartnerInfo("M....");
            daoFactory.getPartnerDAO().update(partner);
            daoFactory.getPartnerDAO().delete(partner.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestMaterial() {
        try {
            Material material = Material.builder()
                    .ruNameMaterial("Copper")
                    .build();

            daoFactory.getMaterialDAO().save(material);
            material = null;
            List<Material> list = daoFactory.getMaterialDAO().findAll();
            material = null;
            material = daoFactory.getMaterialDAO().getById(list.get(list.size()-1).getId());
            material.setEnNameMaterial("rrr...");
            daoFactory.getMaterialDAO().update(material);
            daoFactory.getMaterialDAO().delete(material.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestImage() {
        try {
            Image image = Image.builder()
                    .path("Path")
                    .artObjectId(4L)
                    .build();

            daoFactory.getImageDAO().save(image);
            image = null;
            List<Image> list = daoFactory.getImageDAO().getForPage(100,1);
            image = null;
            image = daoFactory.getImageDAO().getById(list.get(list.size()-1).getId());
            image.setPath("M....");
            daoFactory.getImageDAO().update(image);
            daoFactory.getMaterialDAO().delete(image.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestFormArt() {
        try {
            FormArt formArt  = FormArt.builder()
                    .ruNameFormArt("Tree")
                    .build();

            daoFactory.getFormArtDAO().save(formArt);
            formArt = null;
            List<FormArt> list = daoFactory.getFormArtDAO().findAll();
            formArt = null;
            formArt = daoFactory.getFormArtDAO().getById(list.get(list.size()-1).getId());
            formArt.setEnNameFormArt("M....");
            daoFactory.getFormArtDAO().update(formArt);
            daoFactory.getFormArtDAO().delete(formArt.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestAuthor() {
        try {
            Author author  = Author.builder()
                    .alias("Ivan")
                    .contract("25C")
                    .dateContract(Date.valueOf(LocalDate.now()))
                    .user(daoFactory.getUserDAO().getById(2L))
                    .biography("Was was")
                    .build();

            daoFactory.getAuthorDAO().save(author);
            author = null;
            List<Author> list = daoFactory.getAuthorDAO().getForPage(100,1);
            author = null;
            author = daoFactory.getAuthorDAO().getById(list.get(list.size()-1).getId());
            author.setBiography("M....");
            daoFactory.getAuthorDAO().update(author);
            daoFactory.getAuthorDAO().delete(author.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestArtObject() {
        try {
            ArtObject artObject= ArtObject.builder()
                    .formArt(daoFactory.getFormArtDAO().getById(1L))
                    .material(daoFactory.getMaterialDAO().getById(1L))
                    .author(daoFactory.getAuthorDAO().getById(1L))
                    .dateCreation(Date.valueOf(LocalDate.now()))
                    .descriptionObject("O_o")
                    .objectInfo("Info")
                    .objectName("АбБаG")
                    .build();

            daoFactory.getArtObjectDAO().save(artObject);
            artObject = null;
            List<ArtObject> list = daoFactory.getArtObjectDAO().getByNameLike("бАG");
            artObject = list.get(0);
            artObject.setObjectInfo("M....");
            daoFactory.getArtObjectDAO().update(artObject);
            daoFactory.getArtObjectDAO().delete(list.get(list.size()-1).getId());
            ArtObject ao = daoFactory.getArtObjectDAO().getById(artObject.getId());

        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestAddress() {
        try {
            Address address  = Address.builder()
                    .building("2")
                    .city("Mmm")
                    .userId(5L)
                    .build();

            daoFactory.getAddressDAO().save(address);
            address = null;
            List<Address> list = daoFactory.getAddressDAO().getByUserId(5L);
            address = list.get(0);
            address.setFlat("8");
            daoFactory.getAddressDAO().update(address);
            daoFactory.getAddressDAO().delete(address.getId());
        }catch (DAOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void requestBedArtObj() {
//        try {
//            List<ArtObject> list = daoFactory.getArtObjectDAO().getForPage(100,1);
//            System.out.println(list.size());
//            list.forEach(System.out::println);
//        }catch (DAOException e){
//            e.printStackTrace();
//        }

//        try {
//            List<Image> list = daoFactory.getImageDAO().getForPage(100,1);
//            System.out.println(list.size());
//            list.forEach(System.out::println);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

//        try {
//            List<Author> list = daoFactory.getAuthorDAO().getForPage(100,1);
//            System.out.println(list.size());
//            list.forEach(System.out::println);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        List<Partner> list = null;
        try {
            list = daoFactory.getPartnerDAO().getForPage(10, 1);
            System.out.println(list.size());
            list.forEach(System.out::println);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}