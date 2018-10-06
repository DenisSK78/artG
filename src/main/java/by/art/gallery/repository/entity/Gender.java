package by.art.gallery.repository.entity;

import java.util.Arrays;
import java.util.List;

public enum  Gender{
    MALE,
    FEMALE;

    public static List<Gender> getValues() {
        return Arrays.asList(Gender.values());
    }
}
