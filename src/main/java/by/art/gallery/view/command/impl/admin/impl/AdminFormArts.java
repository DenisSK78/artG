package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.service.FormArtService;
import by.art.gallery.service.ServiceFactory;
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
import static by.art.gallery.view.AttributeName.PAGE_NUMBER;
import static by.art.gallery.view.AttributeName.SUM_RESULTS_FOR_PAGE;

public class AdminFormArts implements AdminCommand {

    private final static Logger logger = LoggerFactory.getLogger(AdminFormArts.class);
    private static final String FORMS_ART = "formsArt";
    private static final String NEW = "new";
    public static final String ID = "id";
    public static final String FORM_ART = "formArt";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        FormArtService formArtService = factory.getFormArtService();

        if (request.getParameter(ID)!=null){
            String id = request.getParameter(ID);
            if (id.equals(NEW)){
                request.setAttribute(FORM_ART, new FormArt());
            }else {
                request.setAttribute(FORM_ART, formArtService.getById(Long.valueOf(id)));
            }
        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<FormArt> formsArt = formArtService.getForPage(amount, page);
            Long count = formArtService.count();
            request.setAttribute(FORMS_ART, formsArt);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, count);
        }
    }
}
