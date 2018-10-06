package by.art.gallery.repository.entity;

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
public class Partner implements Serializable {
    private static final long serialVersionUID = 8118081498910792261L;
    private Long id;
    private String partnerInfo;
    private Subcontractor subcontractor;
    private User user;
}
