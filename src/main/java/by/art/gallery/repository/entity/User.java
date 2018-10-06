package by.art.gallery.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -3717350790758908835L;
    private Long id;
    private Role role;
    private String email;
    private Status status;
    private Gender gender;
    private String surname;
    private String password;
    private String firstName;
    private Date dateBirthday;
}