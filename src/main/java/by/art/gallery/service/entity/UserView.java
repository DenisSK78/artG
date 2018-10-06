package by.art.gallery.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserView implements Serializable {
    private static final long serialVersionUID = 5827546279294659024L;
    private Long id;
    private String role;
    private String email;
    private String status;
    private String gender;
    private String surname;
    private String firstName;
    private Date dateBirthday;
}
