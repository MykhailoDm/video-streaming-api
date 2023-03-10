package com.videostreamingapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "video", indexes = @Index(name = "idx_video_title", columnList = "title"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video extends BaseAuditingEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "video", nullable = false, columnDefinition = "mediumblob")
    private byte[] video;

    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "video_tag",
            joinColumns = { @JoinColumn(name = "video_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id") }
    )
    Set<Tag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
