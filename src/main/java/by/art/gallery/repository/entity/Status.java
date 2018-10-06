package by.art.gallery.repository.entity;

import java.util.Arrays;
import java.util.List;

public enum Status{
    OK,
    BAN;

    public static List<Status> getValues() {
        return Arrays.asList(Status.values());
    }
}
