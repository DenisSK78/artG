package by.art.gallery.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ArtObjectUpdate {
    private Long id;
    private String objectName;
    private String descriptionObject;
    private Date dateCreation;
    private String objectInfo;
    private Long author;
    private Long formArt;
    private Long material;
}
