package by.art.gallery.service.entity;

import by.art.gallery.repository.entity.FormArt;
import by.art.gallery.repository.entity.Material;
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
public class ArtObjectView implements Serializable {
    private static final long serialVersionUID = 8778837204079764557L;
    private Long id;
    private String objectName;
    private String descriptionObject;
    private Date dateCreation;
    private String objectInfo;
    private AuthorView authorView;
    private FormArt formArt;
    private Material material;
}
