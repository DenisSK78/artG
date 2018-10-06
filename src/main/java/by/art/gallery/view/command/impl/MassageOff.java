package by.art.gallery.view.command.impl;

import by.art.gallery.view.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.MESSAGE;

public class MassageOff implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(MESSAGE);
    }
}
