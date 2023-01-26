package com.videostreamingapi.entity;

import com.videostreamingapi.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "role", indexes = @Index(name = "idx_role_user_role", columnList = "userRole"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, unique = true)
    private UserRole userRole;
}
