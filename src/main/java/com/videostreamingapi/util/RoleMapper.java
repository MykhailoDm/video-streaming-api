package com.videostreamingapi.util;

import com.videostreamingapi.entity.Role;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {

    public static Set<String> rolesToRolesStrings(Set<Role> roles) {
        return roles.stream()
                .map(r -> r.getUserRole().name())
                .collect(Collectors.toUnmodifiableSet());
    }
}
