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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ArtObject implements Serializable {
    private static final long serialVersionUID = 3082942018654656741L;
    private Long id;
    private String objectName;
    private String descriptionObject;
    private Date dateCreation;
    private String objectInfo;
    private Author author;
    private FormArt formArt;
    private Material material;
}
