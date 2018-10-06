//package by.art.gallery.view.filter;
//
//import by.art.gallery.service.entity.UserAuthentication;
//import by.art.gallery.view.AttributeName;
//import by.art.gallery.view.Controller;
//import by.art.gallery.view.command.impl.DoPath;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(filterName = "AdminPage", urlPatterns = "/gallery")
//public class AdminPageFilter implements Filter {
//
//    private static final String ADMIN = "admin";
//    private static final String COMMAND_PARAM_NAME = Controller.getCommandParamName();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        if (request.getParameter(COMMAND_PARAM_NAME).equals(ADMIN)) {
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            if (request.getSession().getAttribute(AttributeName.USER_SESSION) != null) {
//                UserAuthentication user = (UserAuthentication) request.getSession().getAttribute(AttributeName.USER_SESSION);
//                if (user.getRole().toLowerCase().equals(ADMIN)) {
//                    filterChain.doFilter(servletRequest, servletResponse);
//                } else {
//                    response.sendRedirect(DoPath.pathForRedirect(request, DoPath.LOGIN));
//                }
//            }else {
//                response.sendRedirect(DoPath.pathForRedirect(request, DoPath.LOGIN));
//            }
//        }else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {}
//}
