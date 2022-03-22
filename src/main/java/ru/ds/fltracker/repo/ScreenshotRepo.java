package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;

import java.util.List;

@Repository
public interface ScreenshotRepo extends JpaRepository<ScreenshotEntity, Long> {
    List<ScreenshotEntity> findAllBySession(SessionEntity sessionEntity);
}
