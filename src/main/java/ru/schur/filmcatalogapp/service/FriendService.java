package ru.schur.filmcatalogapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FriendConverter;
import ru.schur.filmcatalogapp.dto.FriendDTO;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.FriendRepository;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;
    private final FriendConverter friendConverter;

    public FriendService(FriendRepository friendRepository,
                         @Lazy UserService userService,
                         FriendConverter friendConverter) {
        this.friendRepository = friendRepository;
        this.userService = userService;
        this.friendConverter = friendConverter;
    }

    public FriendDTO addFriend(FriendDTO friendDTO) {
        MyUser currentUser = userService.getUser(friendDTO.getUserId());
        MyUser otherUser = userService.getUser(friendDTO.getUserFriendId());
        Friend currentUserFriend = new Friend();
        currentUserFriend.setUser(currentUser);
        currentUserFriend.setFriend(otherUser);
        currentUserFriend.setAllowed(friendDTO.isAllowed());
        Friend otherUserFriend = new Friend();
        otherUserFriend.setUser(otherUser);
        otherUserFriend.setFriend(currentUser);
        otherUserFriend.setAllowed(friendDTO.isAllowed());
        return friendConverter.toFriendDTO(friendRepository.save(currentUserFriend));
    }

    public Friend findFriendById(Long id, Long friendId){
        return friendRepository.findFriendById(id, friendId);
    }

}
