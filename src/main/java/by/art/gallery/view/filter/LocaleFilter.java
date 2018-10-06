package by.art.gallery.view.filter;

import by.art.gallery.service.config.LocaleRegistry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.LOCALE_SESSION;

@WebFilter(filterName = "Locale", urlPatterns = "/*")
public class LocaleFilter implements Filter {

    private static final String BUTTON_LOCALE = "but_loc";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getSession() != null && request.getSession().getAttribute(LOCALE_SESSION) != null) {
            servletResponse.setLocale(LocaleRegistry.getLocale(request.getSession()
                    .getAttribute(LOCALE_SESSION).toString()));
        }else if(request.getSession() != null && request.getSession().getAttribute(LOCALE_SESSION) == null){
            servletResponse.setLocale(LocaleRegistry.getLocale(servletRequest.getLocale().toString()));
            request.getSession().setAttribute(LOCALE_SESSION,
                    LocaleRegistry.getLocale(servletRequest.getLocale().toString()));
        }else{
            request.getSession(true);
            servletResponse.setLocale(LocaleRegistry.getLocale(servletRequest.getLocale().toString()));
            request.getSession().setAttribute(LOCALE_SESSION,
                    LocaleRegistry.getLocale(servletRequest.getLocale().toString()));
        }

        if (request.getParameter(BUTTON_LOCALE) != null){
            servletResponse.setLocale(LocaleRegistry.getLocale(request.getParameter(BUTTON_LOCALE)));
            request.getSession().setAttribute(LOCALE_SESSION, request.getParameter(BUTTON_LOCALE));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
