package com.videostreamingapi.util;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.entity.User;
import lombok.experimental.UtilityClass;

import static com.videostreamingapi.util.RoleMapper.rolesToRolesStrings;

@UtilityClass
public class UserMapper {

    public static UserInfoResponse userToUserInfoResponse(User user) {
        return new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail(),
                rolesToRolesStrings(user.getRoles()), user.getCreatedBy(), user.getLastModifiedBy(),
                user.getCreatedDate(), user.getLastModifiedDate());
    }


}
