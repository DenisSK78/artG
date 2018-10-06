package by.art.gallery.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserAuthentication implements Serializable {
    private static final long serialVersionUID = 3497101838831927737L;
    private Long id;
    private String role;
    private String status;
}
