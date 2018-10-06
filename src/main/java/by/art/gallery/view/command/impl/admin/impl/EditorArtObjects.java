package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.service.ArtObjectService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class EditorArtObjects implements AdminCommand {

    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        System.out.println("Editor-art-object");
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()){
            System.out.println(en.nextElement());
        }

        System.out.println(request.getParameter("formArtId"));
        System.out.println(request.getParameter("materialId"));

        ServiceFactory factory = ServiceFactory.getInstance();
        ArtObjectService artObjectService = factory.getArtObjectService();

        Long id = request.getParameter(ID).equals("")
                ? null : Long.valueOf(request.getParameter(ID));

//        objectName
//        dateCreation
//        formArtID
//        materialID
//        id

//        if(request.getParameter(DELETE) != null){
//            artObjectService.delete(id);
//        }
//
//        if (request.getParameter(ADD) != null){
//            ArtObjectUpdate artObject = ArtObjectUpdate.builder()
//                    .objectName(request.getParameter("objectName"))
//                    .dateCreation(Date.valueOf(request.getParameter("dateCreation")))
//                    .author(Long.valueOf(request.getParameter("author")))
//                    .formArt(Long.valueOf(request.getParameter("formArtId")))
//                    .material(Long.valueOf(request.getParameter("materialId")))
//                    .descriptionObject(request.getParameter("descriptionObj"))
//                    .build();
//            artObject.save(artObject);
//        }
//
//        if (request.getParameter(UPDATE) != null){
//            ArtObjectUpdate artObject = ArtObjectUpdate.builder()
//                    .objectName(request.getParameter("objectName"))
//                    .dateCreation(Date.valueOf(request.getParameter("dateCreation")))
//                    .author(Long.valueOf(request.getParameter("author")))
//                    .formArt(Long.valueOf(request.getParameter("formArtId")))
//                    .material(Long.valueOf(request.getParameter("materialId")))
//                    .descriptionObject(request.getParameter("descriptionObj"))
//                    .build();
//            artObject.update(artObject);
//        }
    }
}
