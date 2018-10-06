package by.art.gallery.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorView implements Serializable {
    private static final long serialVersionUID = 7884394562372685463L;
    private Long id;
    private String alias;
    private String contract;
    private Date dateContract;
    private String biography;
    private UserView userView;
}
