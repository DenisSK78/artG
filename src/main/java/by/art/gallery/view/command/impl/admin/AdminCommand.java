package by.art.gallery.view.command.impl.admin;

import by.art.gallery.repository.exception.DAOException;
import by.art.gallery.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AdminCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
