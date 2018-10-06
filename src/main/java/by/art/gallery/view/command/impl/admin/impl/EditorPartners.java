package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditorPartners implements AdminCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        System.out.println("Editor partners");
    }
}
