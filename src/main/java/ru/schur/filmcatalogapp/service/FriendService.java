package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FriendConverter;
import ru.schur.filmcatalogapp.dto.FriendDTO;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.repository.FriendRepository;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;
    private final FriendConverter friendConverter;

    public FriendService(FriendRepository friendRepository,
                         UserService userService,
                         FriendConverter friendConverter) {
        this.friendRepository = friendRepository;
        this.userService = userService;
        this.friendConverter = friendConverter;
    }

    public FriendDTO addFriend(FriendDTO friendDTO) {
        Friend friend = new Friend();
        friend.setUser(userService.getUser(friendDTO.getUserId()));
        friend.setFriend(userService.getUser(friendDTO.getUserFriendId()));
        friend.setAllowed(friendDTO.isAllowed());
        return friendConverter.toFriendDTO(friendRepository.save(friend));
    }
}
