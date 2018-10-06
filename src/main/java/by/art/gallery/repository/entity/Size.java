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
public class Size implements Serializable {
    private static final long serialVersionUID = 6524443094824467111L;
    private Integer width;
    private Integer length;
    private Integer height;
    private Integer weight;
    private Long artObjectId;

    public Integer getWidth() {
        return width == null ? 0 : width;
    }

    public Integer getLength() {
        return length == null ? 0 : length;
    }

    public Integer getHeight() {
        return height == null ? 0 : height;
    }

    public Integer getWeight() {
        return weight == null ? 0 : weight;
    }

    public Long getArtObjectId() {
        return artObjectId;
    }
}
