package by.art.gallery.view.command.impl.admin.impl;

import by.art.gallery.repository.entity.Address;
import by.art.gallery.service.AddressService;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.UserService;
import by.art.gallery.service.converter.ConverterArtObject;
import by.art.gallery.service.converter.ConverterEnum;
import by.art.gallery.service.entity.UserView;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.art.gallery.view.AttributeName.AMOUNT_FOR_PAGE;
import static by.art.gallery.view.AttributeName.PAGE_NUMBER;
import static by.art.gallery.view.AttributeName.SUM_RESULTS_FOR_PAGE;

public class AdminUsers implements AdminCommand {

    private static final String ID = "id";
    private static final String ADDRESSES = "addresses";
    private static final String USER = "user";
    private static final String USERS = "users";
    private static final String STATUSES = "statuses";
    private static final String ROLES = "roles";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        if(request.getParameter(ID) != null){
            AddressService addressService = factory.getAddressService();
            Long id = Long.valueOf(request.getParameter(ID));
            UserView user = userService.getUserById(id);
            List<Address> addresses = addressService.getAddressesByUserId(id);
            request.setAttribute(STATUSES, ConverterEnum.getStatuses());
            request.setAttribute(ROLES, ConverterEnum.getRoles());
            request.setAttribute(USER, user);
            request.setAttribute(ADDRESSES, addresses);
        }else {
            Integer page = 1;
            Integer amount = 10;

            if (request.getParameter(PAGE_NUMBER) != null) {
                page = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            }

            List<UserView> users = userService.getForPage(amount, page);
            Long countUser = userService.count();
            request.setAttribute(USERS, users);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(AMOUNT_FOR_PAGE, amount);
            request.setAttribute(SUM_RESULTS_FOR_PAGE, countUser);
        }
    }
}
