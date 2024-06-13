package com.chatopbackend.chatopbackend.mapping;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreated_at(DateUtils.convertDateToString(user.getCreatedAt()));
        userDto.setUpdated_at(DateUtils.convertDateToString(user.getUpdatedAt()));
        return userDto;
    }
}
