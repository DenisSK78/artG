package by.art.gallery.service.entity;

import by.art.gallery.repository.entity.Subcontractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PartnerView implements Serializable {
    private static final long serialVersionUID = -3733866536078071016L;
    private Long id;
    private String partnerInfo;
    private Subcontractor subcontractor;
    private UserView userView;
}
