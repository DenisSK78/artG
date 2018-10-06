package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.SubcontractorService;
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

public class AdminSubcontractors implements AdminCommand {

    private final static Logger logger = LoggerFactory.getLogger(AdminSubcontractors.class);
    private static final String SUBCONTRACTORS = "subcontractors";
    private static final String SUBCONTRACTOR = "subcontractor";
    private static final String ID = "id";
    private static final String NEW = "new";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        SubcontractorService subcontractorService = factory.getSubcontractorService();

        if (request.getParameter(ID)!=null){
            String id = request.getParameter(ID);

            if (id.equals(NEW)){
                request.setAttribute(SUBCONTRACTOR, new Subcontractor());
            }else {
                request.setAttribute(SUBCONTRACTOR, subcontractorService.getById(Long.valueOf(id)));
            }

        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<Subcontractor> subcontractors = subcontractorService.getForPage(amount, page);
            Long count = subcontractorService.count();
            request.setAttribute(SUBCONTRACTORS, subcontractors);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, count);
        }
    }
}
