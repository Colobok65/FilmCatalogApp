package ru.schur.filmcatalogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDTO {

    private Long id;
    private Long userId;
    private Long userFriendId;
    private boolean isAllowed;

}
