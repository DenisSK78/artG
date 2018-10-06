package by.art.gallery.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@ToString
public class Address implements Serializable {
    private static final long serialVersionUID = 6993901913535028978L;
    private Long id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String building;
    private String flat;
    private String phone;
    private String postCode;
    private Long userId;
}
