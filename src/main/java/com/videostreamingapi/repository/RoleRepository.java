package com.videostreamingapi.repository;

import com.videostreamingapi.entity.Role;
import com.videostreamingapi.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByUserRole(UserRole userRole);
}
