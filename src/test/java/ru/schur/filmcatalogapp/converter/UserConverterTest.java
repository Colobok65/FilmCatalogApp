package ru.schur.filmcatalogapp.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.UserRepository;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

class UserConverterTest {

    private final UserRepository userRepositoryMock = mock(UserRepository.class);
    private final UserConverter userConverter = new UserConverter(userRepositoryMock);

    @Test
    void toUserDTO() {

        UserDTO expected = new UserDTO(
                10L,
                "name",
                "avatar",
                new ArrayList<>(),
                new ArrayList<>());

        MyUser user = new MyUser();
        user.setId(10L);
        user.setName("name");
        user.setAvatar("avatar");
        user.setFriends(new ArrayList<>());
        user.setFavouriteFilms(new ArrayList<>());

        UserDTO actual = userConverter.toUserDTO(user);

        Assertions.assertEquals(expected, actual);
    }
}