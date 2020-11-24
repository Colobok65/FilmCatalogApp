package ru.schur.filmcatalogapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.schur.filmcatalogapp.converter.CommentConverter;
import ru.schur.filmcatalogapp.converter.FilmCategoryConverter;
import ru.schur.filmcatalogapp.converter.FilmConverter;
import ru.schur.filmcatalogapp.converter.UserConverter;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.exception.AccessIsNotAllowedException;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchUserException;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.FilmCategoryRepository;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



class UserServiceTest {
    private static final String USERNAME = "Username";

    private final UserRepository userRepositoryMock = mock(UserRepository.class);
    private final UserConverter userConverterMock = mock(UserConverter.class);
    private final FilmService filmServiceMock = mock(FilmService.class);
    private final FriendService friendServiceMock = mock(FriendService.class);
    private final FilmCategoryRepository filmCategoryRepositoryMock = mock(FilmCategoryRepository.class);
    private final FilmConverter filmConverterMock = new FilmConverter(
            new CommentConverter(),
            new FilmCategoryConverter(filmCategoryRepositoryMock)
    );

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = spy(new UserService(
                userRepositoryMock,
                userConverterMock,
                filmServiceMock,
                friendServiceMock,
                filmConverterMock
        ));
        doReturn(USERNAME).when(userService).getCurrentUserName();
    }

    @Nested
    @DisplayName("getUserFilms")
    public class GetUserFilms {
        @Test
        @DisplayName(" Should do something")
        void shouldReturnFriendsFilmCollection() {
            Long userId = 10L;
            List<FilmDTO> expected = new ArrayList<>();
            expected.add(new FilmDTO(
                    20L,
                    "Film 1",
                    "ing",
                    "date",
                    "description",
                    3,
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            MyUser currentUser = new MyUser();
            currentUser.setId(30L);
            MyUser friendUser = new MyUser();
            friendUser.setId(40L);
            ArrayList<Film> favouriteFilms = new ArrayList<>();
            Film film = new Film();
            film.setId(20L);
            film.setName("Film 1");
            film.setPoster("ing");
            film.setDateOfCreate("date");
            film.setDescription("description");
            film.setRating(3);
            film.setCategories(new ArrayList<>());
            film.setComments(new ArrayList<>());
            favouriteFilms.add(film);
            friendUser.setFavouriteFilms(favouriteFilms);
            Friend friend = new Friend();
            friend.setAllowed(true);
            when(userRepositoryMock.findByLogin(any())).thenReturn(currentUser);
            when(friendServiceMock.findFriendById(any(), any())).thenReturn(friend);
            when(userRepositoryMock.findById(any())).thenReturn(Optional.of(friendUser));

            List<FilmDTO> actualUserFilms = userService.getUserFilms(userId);

            verify(userService).getCurrentUserName();
            verify(userRepositoryMock).findByLogin(USERNAME);
            verify(friendServiceMock).findFriendById(10L, 30L);
            verify(userRepositoryMock).findById(10L);
            assertEquals(expected, actualUserFilms);
        }

        @Test()
        void getUserFilms_ShouldThrow_ThereIsNoSucUserException() throws ThereIsNoSuchUserException {

            when(userRepositoryMock.findByLogin(any())).thenReturn(null);

            assertThrows(ThereIsNoSuchUserException.class, () -> userService.getUserFilms(10L));

            verify(userService).getCurrentUserName();
            verify(userRepositoryMock).findByLogin(USERNAME);
        }

        @Test
        void getUserFilms_ShouldThrow_AccessIsNotAllowedException_IfFriendIsNotAllowed() {

            Long userId = 10L;
            MyUser currentUser = new MyUser();
            currentUser.setId(30L);
            MyUser friendUser = new MyUser();
            friendUser.setId(40L);
            ArrayList<Film> favouriteFilms = new ArrayList<>();
            Film film = new Film();
            film.setId(20L);
            film.setName("Film 1");
            film.setPoster("ing");
            film.setDateOfCreate("date");
            film.setDescription("description");
            film.setRating(3);
            film.setCategories(new ArrayList<>());
            film.setComments(new ArrayList<>());
            favouriteFilms.add(film);
            friendUser.setFavouriteFilms(favouriteFilms);
            Friend friend = new Friend();
            friend.setAllowed(false);
            when(userRepositoryMock.findByLogin(any())).thenReturn(currentUser);
            when(friendServiceMock.findFriendById(any(), any())).thenReturn(friend);

            assertThrows(AccessIsNotAllowedException.class,
                    () -> userService.getUserFilms(userId));

            verify(userService).getCurrentUserName();

        }

        @Test
        void getUserFilms_ShouldThrow_AccessIsNotAllowedException_IfFriendIsNull(){
            Long userId = 10L;
            MyUser currentUser = new MyUser();
            currentUser.setId(30L);
            MyUser friendUser = new MyUser();
            friendUser.setId(40L);
            ArrayList<Film> favouriteFilms = new ArrayList<>();
            Film film = new Film();
            film.setId(20L);
            film.setName("Film 1");
            film.setPoster("ing");
            film.setDateOfCreate("date");
            film.setDescription("description");
            film.setRating(3);
            film.setCategories(new ArrayList<>());
            film.setComments(new ArrayList<>());
            favouriteFilms.add(film);
            friendUser.setFavouriteFilms(favouriteFilms);

            when(userRepositoryMock.findByLogin(any())).thenReturn(currentUser);
            when(friendServiceMock.findFriendById(any(), any())).thenReturn(null);

            assertThrows(AccessIsNotAllowedException.class,
                    () -> userService.getUserFilms(userId));

            verify(userService).getCurrentUserName();

        }

        @Test
        void getUserFilms_ShouldThrow_Exception_IfUserNotFoundById(){

            Long userId = 10L;
            MyUser currentUser = new MyUser();
            currentUser.setId(30L);
            MyUser friendUser = new MyUser();
            friendUser.setId(40L);
            ArrayList<Film> favouriteFilms = new ArrayList<>();
            Film film = new Film();
            film.setId(20L);
            film.setName("Film 1");
            film.setPoster("ing");
            film.setDateOfCreate("date");
            film.setDescription("description");
            film.setRating(3);
            film.setCategories(new ArrayList<>());
            film.setComments(new ArrayList<>());
            favouriteFilms.add(film);
            friendUser.setFavouriteFilms(favouriteFilms);
            Friend friend = new Friend();
            friend.setAllowed(true);

            when(userRepositoryMock.findByLogin(any())).thenReturn(currentUser);
            when(friendServiceMock.findFriendById(any(), any())).thenReturn(friend);
            when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());


            assertThrows(ThereIsNoSuchUserException.class,
                    () -> userService.getUserFilms(userId));

            verify(userService).getCurrentUserName();
            verify(userRepositoryMock).findByLogin(USERNAME);
            verify(friendServiceMock).findFriendById(10L, 30L);

        }
    }
}