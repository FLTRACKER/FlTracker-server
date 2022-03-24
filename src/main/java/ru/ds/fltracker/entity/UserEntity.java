package ru.ds.fltracker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Basic
    @Column(name = "full_name", length = 255)
    private String fullName;

    @Basic
    @Column(name = "email", length = 255)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SessionEntity> sessions;
}
