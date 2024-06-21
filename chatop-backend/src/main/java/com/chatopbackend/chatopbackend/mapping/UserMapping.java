package com.chatopbackend.chatopbackend.mapping;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    /**
     * Maps a User to a UserDto
     * @param user
     * @return
     */
    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created_at(DateUtils.convertDateToString(user.getCreatedAt()))
                .updated_at(DateUtils.convertDateToString(user.getUpdatedAt()))
                .build();
    }
}
