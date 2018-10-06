package by.art.gallery.view.command.impl;

import by.art.gallery.view.Controller;

import javax.servlet.http.HttpServletRequest;

public final class DoPath {

    static final String MAIN = "/WEB-INF/jsp/main.jsp";
    static final String NEWS = "/WEB-INF/jsp/news.jsp";
    static final String LOGIN = "/WEB-INF/jsp/login.jsp";
    static final String ADMIN = "/WEB-INF/jsp/admin.jsp";
    static final String AUTHORS = "/WEB-INF/jsp/authors.jsp";
    static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";

    private DoPath(){}

    static String pathForRedirect(HttpServletRequest request, String doPath){
        return String.valueOf(request.getRequestURL()) +
                "?" +
                Controller.getCommandParamName() +
                "=" +
                doPath.subSequence(13, doPath.length() - 4);
    }
}