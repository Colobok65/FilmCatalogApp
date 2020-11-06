package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.FriendDTO;
import ru.schur.filmcatalogapp.model.Friend;

@Service
public class FriendConverter {

    public FriendDTO toFriendDTO(Friend friend){
        return new FriendDTO(
                friend.getId(),
                friend.getUser().getId(),
                friend.getFriend().getId(),
                friend.isAllowed()
        );
    }
}
