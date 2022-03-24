package ru.ds.fltracker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "sessions")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Basic
    @Column(name = "finish_time", nullable = true)
    private LocalDateTime finishTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "username")
    private UserEntity user;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<BreakEntity> breaks;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<ScreenshotEntity> screenshots;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<ActivityEntity> activities;

    public static SessionEntity of(Long id) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(id);
        return sessionEntity;
    }
}
