package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.repo.ActivityRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepo activityRepo;
    private final EntityManager em;

    public ActivityEntity save(ActivityEntity activityEntity) {
        ActivityEntity persisted = activityRepo.saveAndFlush(activityEntity);
        em.refresh(persisted);
        return persisted;
    }

    public ActivityEntity findActivityById(Long id) {
        return activityRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
