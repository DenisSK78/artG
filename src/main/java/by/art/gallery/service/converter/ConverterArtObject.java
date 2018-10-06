package by.art.gallery.service.converter;

import by.art.gallery.repository.entity.ArtObject;
import by.art.gallery.service.entity.ArtObjectView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConverterArtObject {

    public static ArtObjectView toArtObjectView(ArtObject artObject){
        return ArtObjectView.builder()
                .id(artObject.getId())
                .objectName(artObject.getObjectName())
                .descriptionObject(artObject.getDescriptionObject())
                .dateCreation(artObject.getDateCreation())
                .objectInfo(artObject.getObjectInfo())
                .authorView(ConverterAuthor.toAuthorView(artObject.getAuthor()))
                .formArt(artObject.getFormArt())
                .material(artObject.getMaterial())
                .build();
    }

    public static List<ArtObjectView> toArtObjectsView(List<ArtObject> artObjects){
        return artObjects.stream()
                .map(ConverterArtObject::toArtObjectView)
                .collect(toList());
    }
}