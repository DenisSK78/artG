package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.service.PartnerService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.SubcontractorService;
import by.art.gallery.service.entity.PartnerView;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.art.gallery.view.AttributeName.AMOUNT_FOR_PAGE;
import static by.art.gallery.view.AttributeName.PAGE_NUMBER;
import static by.art.gallery.view.AttributeName.SUBCONTRACTORS;
import static by.art.gallery.view.AttributeName.SUM_RESULTS_FOR_PAGE;

public class AdminPartners implements AdminCommand {

    private static final String PARTNERS = "partners";
    private static final String ID = "id";
    private static final String PARTNER = "partner";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        PartnerService partnerService = factory.getPartnerService();
        SubcontractorService subService = factory.getSubcontractorService();

        if(request.getParameter(ID) != null){
            Long id = Long.valueOf(request.getParameter(ID));
            PartnerView partner = partnerService.getById(id);
            List<Subcontractor> subcontractors = subService.getAll();
            request.setAttribute(SUBCONTRACTORS, subcontractors);
            request.setAttribute(PARTNER, partner);
        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<PartnerView> partners = partnerService.getForPage(amount, page);
            Long countUser = partnerService.count();
            request.setAttribute(PARTNERS, partners);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, countUser);
        }
    }
}
