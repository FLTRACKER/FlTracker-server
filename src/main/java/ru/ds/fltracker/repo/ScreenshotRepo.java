package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.ScreenshotEntity;

@Repository
public interface ScreenshotRepo extends JpaRepository<ScreenshotEntity, Long> {
}
