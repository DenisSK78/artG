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
public class FormArt implements Serializable {
    private static final long serialVersionUID = -5458762473126703844L;
    private Long id;
    private String ruNameFormArt;
    private String enNameFormArt;
}
