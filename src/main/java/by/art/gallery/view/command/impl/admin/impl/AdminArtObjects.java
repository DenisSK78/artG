package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.entity.Material;
import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.ArtObjectService;
import by.art.gallery.service.AuthorService;
import by.art.gallery.service.FormArtService;
import by.art.gallery.service.MaterialService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.entity.ArtObjectView;
import by.art.gallery.service.entity.AuthorView;
import by.art.gallery.service.entity.AuthorsSelect;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.art.gallery.view.AttributeName.AMOUNT_FOR_PAGE;
import static by.art.gallery.view.AttributeName.FORMS_ART;
import static by.art.gallery.view.AttributeName.MATERIALS;
import static by.art.gallery.view.AttributeName.PAGE_NUMBER;
import static by.art.gallery.view.AttributeName.SUM_RESULTS_FOR_PAGE;

public class AdminArtObjects implements AdminCommand {

    private final static Logger logger = LoggerFactory.getLogger(AdminArtObjects.class);
    private static final String ART_OBJECTS = "artObjects";
    private static final String ID = "id";
    private static final String ART_OBJECT = "artObject";
    private static final String AUTHORS_SELECT = "authorsSelect";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        ArtObjectService artObjectService = factory.getArtObjectService();
        FormArtService formArtService = factory.getFormArtService();
        MaterialService materialService = factory.getMaterialService();
        AuthorService authorService = factory.getAuthorService();

        if(request.getParameter(ID) != null){
            Long id = Long.valueOf(request.getParameter(ID));
            ArtObjectView artObject = artObjectService.getById(id);
            List<FormArt> formArts = formArtService.getAll();
            List<Material> materials = materialService.getAll();

            List<AuthorsSelect> authorsSelect = authorService.getAuthorsForSelect();


            request.setAttribute(AUTHORS_SELECT, authorsSelect);
            request.setAttribute(MATERIALS, materials);
            request.setAttribute(FORMS_ART, formArts);
            request.setAttribute(ART_OBJECT, artObject);
        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<ArtObjectView> artObjects = artObjectService.getForPage(amount, page);
            Long count = artObjectService.count();
            request.setAttribute(ART_OBJECTS, artObjects);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, count);
        }
    }
}
