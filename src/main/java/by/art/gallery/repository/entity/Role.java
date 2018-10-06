package by.art.gallery.repository.entity;

import java.util.Arrays;
import java.util.List;

public enum Role{
    GUEST,
    ADMIN,
    USER;

    public static List<Role> getValues() {
        return Arrays.asList(Role.values());
    }
}
