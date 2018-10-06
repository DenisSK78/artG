package by.art.gallery.service;

import by.art.gallery.service.entity.PartnerView;
import by.art.gallery.service.exception.ServiceException;

import java.util.List;

public interface PartnerService {
    Long count() throws ServiceException;
    List<PartnerView> getForPage(Integer amount, Integer pageNumber) throws ServiceException;
    PartnerView getById(Long id) throws ServiceException;
}
