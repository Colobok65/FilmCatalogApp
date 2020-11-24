package ru.schur.filmcatalogapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.schur.filmcatalogapp.dto.FriendDTO;
import ru.schur.filmcatalogapp.service.FriendService;

@RestController
@RequestMapping("/my_app/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add_friend")
    public FriendDTO addFriend(@RequestBody FriendDTO friendDTO){
        return friendService.addFriend(friendDTO);
    }
}
