package by.art.gallery.service.converter;

import by.art.gallery.repository.entity.Gender;
import by.art.gallery.repository.entity.Role;
import by.art.gallery.repository.entity.Status;

import java.util.List;
import java.util.Locale;

public class ConverterEnum {

    public static Status getEnumStatus(String status){
        return Status.valueOf(status.toUpperCase(Locale.ENGLISH));
    }

    public static Role getEnumRole(String role){
        return Role.valueOf(role.toUpperCase(Locale.ENGLISH));
    }

    public static Gender getGender(String gender) {
        return Gender.valueOf(gender.toUpperCase(Locale.ENGLISH));
    }

    public static List<Role> getRoles(){
        return Role.getValues();
    }

    public static List<Status> getStatuses(){
        return Status.getValues();
    }

    public static List<Gender> getGenders(){
        return Gender.getValues();
    }
}
