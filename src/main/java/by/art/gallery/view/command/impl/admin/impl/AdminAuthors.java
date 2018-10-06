package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.service.AuthorService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.entity.AuthorView;
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

public class AdminAuthors implements AdminCommand {

    private final static Logger logger = LoggerFactory.getLogger(AdminAuthors.class);
    private static final String AUTHORS = "authors";
    private static final String ID = "id";
    private static final String AUTHOR = "author";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        AuthorService authorService = factory.getAuthorService();

        if(request.getParameter(ID) != null){
            Long id = Long.valueOf(request.getParameter(ID));
            AuthorView author = authorService.getById(id);
            request.setAttribute(AUTHOR, author);
        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<AuthorView> authors = authorService.getForPage(amount, page);
            Long count = authorService.count();
            request.setAttribute(AUTHORS, authors);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, count);
        }
    }
}
