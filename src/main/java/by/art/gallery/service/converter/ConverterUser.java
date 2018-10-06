package by.art.gallery.service.converter;

import by.art.gallery.repository.entity.User;
import by.art.gallery.service.entity.UserView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConverterUser {

    public static UserView toUserView(User user) {
        return UserView.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .dateBirthday(user.getDateBirthday())
                .gender(user.getGender().toString())
                .build();
    }

    public static List<UserView> toUsersView(List<User> users){
        return users.stream()
                .map(ConverterUser::toUserView)
                .collect(toList());
    }
}
