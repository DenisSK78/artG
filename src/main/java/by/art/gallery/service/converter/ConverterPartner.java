package by.art.gallery.service.converter;

import by.art.gallery.repository.entity.Partner;
import by.art.gallery.service.entity.PartnerView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConverterPartner {

    public static PartnerView toPartnerView(Partner partner){
        return PartnerView.builder()
                .id(partner.getId())
                .partnerInfo(partner.getPartnerInfo())
                .subcontractor(partner.getSubcontractor())
                .userView(ConverterUser.toUserView(partner.getUser()))
                .build();
    }

    public static List<PartnerView> toPartnersView(List<Partner> users){
        return users.stream()
                .map(ConverterPartner::toPartnerView)
                .collect(toList());
    }
}
