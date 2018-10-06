package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.service.FormArtService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.ADD;
import static by.art.gallery.view.AttributeName.DELETE;
import static by.art.gallery.view.AttributeName.UPDATE;

public class EditorFormsArt implements AdminCommand {

    private static final String ID = "id";
    private static final String RU_NAME_FORM_ART = "ruNameFormArt";
    private static final String EN_NAME_FORM_ART = "enNameFormArt";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        FormArtService formArtService = factory.getFormArtService();

        Long id = request.getParameter(ID).equals("")
                ? null : Long.valueOf(request.getParameter(ID));

        if(request.getParameter(DELETE) != null){
           formArtService.delete(id);
        }

        if (request.getParameter(ADD) != null){
            FormArt formArt = FormArt.builder()
                    .ruNameFormArt(request.getParameter(RU_NAME_FORM_ART))
                    .enNameFormArt(request.getParameter(EN_NAME_FORM_ART))
                    .build();
            formArtService.save(formArt);
        }

        if (request.getParameter(UPDATE) != null){
            FormArt formArt = FormArt.builder()
                    .id(id)
                    .ruNameFormArt(request.getParameter(RU_NAME_FORM_ART))
                    .enNameFormArt(request.getParameter(EN_NAME_FORM_ART))
                    .build();
            formArtService.update(formArt);
        }
    }
}
