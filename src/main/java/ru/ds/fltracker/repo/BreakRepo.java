package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.BreakEntity;

@Repository
public interface BreakRepo extends JpaRepository<BreakEntity, Long> {
}
