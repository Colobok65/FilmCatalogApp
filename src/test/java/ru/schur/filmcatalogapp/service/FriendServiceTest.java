package ru.schur.filmcatalogapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.schur.filmcatalogapp.converter.FriendConverter;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.FriendRepository;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FriendServiceTest {

    private final FriendRepository friendRepositoryMock = mock(FriendRepository.class);
    private final UserService userServiceMock = mock(UserService.class);
    private final FriendConverter friendConverterMock = mock(FriendConverter.class);

    private final FriendService friendService =
            new FriendService(friendRepositoryMock, userServiceMock, friendConverterMock);


    @Test
    void findFriendById() {

        MyUser user = new MyUser();
        user.setId(20L);

        MyUser friend = new MyUser();
        friend.setId(30L);

        Friend expected = new Friend();
        expected.setId(10L);
        expected.setUser(user);
        expected.setFriend(friend);
        expected.setAllowed(true);

        when(friendRepositoryMock.findFriendById(any(), any())).thenReturn(expected);

        Friend actual = friendService.findFriendById(20L, 30L);

        Assertions.assertEquals(expected, actual);

        verify(friendRepositoryMock).findFriendById(20L, 30L);
    }
}