package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.Material;
import by.art.gallery.service.MaterialService;
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

public class EditorMaterials implements AdminCommand {

    private static final String ID = "id";
    private static final String RU_NAME_MATERIAL = "ruNameMaterial";
    private static final String EN_NAME_MATERIAL = "enNameMaterial";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        MaterialService formArtService = factory.getMaterialService();

        Long id = request.getParameter(ID).equals("")
                ? null : Long.valueOf(request.getParameter(ID));

        if(request.getParameter(DELETE) != null){
            formArtService.delete(id);
        }

        if (request.getParameter(ADD) != null){
            Material material = Material.builder()
                    .ruNameMaterial(request.getParameter(RU_NAME_MATERIAL))
                    .enNameMaterial(request.getParameter(EN_NAME_MATERIAL))
                    .build();
            formArtService.save(material);
        }

        if (request.getParameter(UPDATE) != null){
            Material material = Material.builder()
                    .id(id)
                    .ruNameMaterial(request.getParameter(RU_NAME_MATERIAL))
                    .enNameMaterial(request.getParameter(EN_NAME_MATERIAL))
                    .build();
            formArtService.update(material);
        }
    }
}
