package ru.ds.fltracker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "activities")
public class ActivityEntity {
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
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private SessionEntity session;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
    private List<ActiveWindowEntity> activeWindows;
}
