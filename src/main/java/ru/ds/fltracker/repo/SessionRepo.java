package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.SessionEntity;

import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<SessionEntity, Long>,
        JpaSpecificationExecutor<SessionEntity> {
}
