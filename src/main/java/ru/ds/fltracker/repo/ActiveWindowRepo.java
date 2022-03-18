package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.ActiveWindowEntity;

@Repository
public interface ActiveWindowRepo extends JpaRepository<ActiveWindowEntity, Long> {
}
