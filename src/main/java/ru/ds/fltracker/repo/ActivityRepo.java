package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.ActivityEntity;

@Repository
public interface ActivityRepo extends JpaRepository<ActivityEntity, Long> {
}
