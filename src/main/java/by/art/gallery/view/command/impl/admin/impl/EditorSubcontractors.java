package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.Subcontractor;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.SubcontractorService;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.ADD;
import static by.art.gallery.view.AttributeName.DELETE;
import static by.art.gallery.view.AttributeName.UPDATE;

public class EditorSubcontractors implements AdminCommand {

    private static final String ID = "id";
    private static final String OCCUPATION = "occupation";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        SubcontractorService subService = factory.getSubcontractorService();

        Long id = request.getParameter(ID).equals("")
                ? null : Long.valueOf(request.getParameter(ID));

        if(request.getParameter(DELETE) != null){
            subService.delete(id);
        }

        if (request.getParameter(ADD) != null){
            Subcontractor sub = Subcontractor.builder()
                    .occupation(request.getParameter(OCCUPATION))
                    .build();
            subService.save(sub);
        }

        if (request.getParameter(UPDATE) != null){
            Subcontractor sub = Subcontractor.builder()
                    .id(id)
                    .occupation(request.getParameter(OCCUPATION))
                    .build();
            subService.update(sub);
        }
    }
}
