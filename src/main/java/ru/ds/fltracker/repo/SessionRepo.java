package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.SessionEntity;

@Repository
public interface SessionRepo extends JpaRepository<SessionEntity, Long> {
}
